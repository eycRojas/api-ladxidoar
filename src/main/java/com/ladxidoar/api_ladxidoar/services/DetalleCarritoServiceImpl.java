package com.ladxidoar.api_ladxidoar.services;

import com.ladxidoar.api_ladxidoar.entities.Carrito;
import com.ladxidoar.api_ladxidoar.entities.DetalleCarrito;
import com.ladxidoar.api_ladxidoar.entities.Producto;
import com.ladxidoar.api_ladxidoar.repository.CarritoRepository;
import com.ladxidoar.api_ladxidoar.repository.DetalleCarritoRepository;
import com.ladxidoar.api_ladxidoar.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleCarritoServiceImpl implements DetalleCarritoService {

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoRepository carritoRepository;


    @Override
    public DetalleCarrito agregarProductoAlCarrito(Long carritoId, Long productoId, int cantidad) {
        DetalleCarrito d = null;
        // Verificar si el producto ya está en el carrito
        Optional<DetalleCarrito> detalleExistente = findByCarritoAndProducto(carritoId, productoId);

        if(detalleExistente.isPresent()) {
            System.out.println("Producto ya existe");
            // Actualizar cantidad si ya existe
            DetalleCarrito detalle = detalleExistente.get();
            detalle.setCantidad(detalle.getCantidad() + cantidad);
            DetalleCarrito detalleCarrito = updateDetalleCarrito(detalle);
            System.out.println("Datos detalle "+ detalleCarrito);
            return detalleCarrito;
        } else {
            System.out.println("Producto no existe");
            // Crear nuevo detalle si no existe
            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            Optional<Carrito> carritoExistente = carritoRepository.findById(carritoId);
            if(carritoExistente.isPresent()) {
                System.out.println("Nuevo detalle");
                // Actualizar carrito
                Carrito carrito = carritoExistente.get();
                DetalleCarrito nuevoDetalle = new DetalleCarrito();
                nuevoDetalle.setCarrito(carrito);
                nuevoDetalle.setProducto(producto);
                nuevoDetalle.setCantidad(cantidad);

                DetalleCarrito detalleCarrito = saveDetalleCarrito(nuevoDetalle);
                System.out.println("Datos nuevo detalle "+ detalleCarrito);
                return detalleCarrito;
            }
            return  d;
        }
    }

    @Override
    public Optional<DetalleCarrito> findByCarritoAndProducto(Long carritoId, Long productoId) {
        return detalleCarritoRepository.findByCarritoIdAndProductoId(carritoId, productoId);
    }

    @Override
    public DetalleCarrito saveDetalleCarrito(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.save(detalleCarrito);
    }

    @Override
    public DetalleCarrito updateDetalleCarrito(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.save(detalleCarrito);
    }

    @Override
    public List<DetalleCarrito> getDetallesCarrito() {
        return detalleCarritoRepository.findAll();
    }

    @Override
    public Optional<DetalleCarrito> getDetalleCarritoById(Long id) {
        return detalleCarritoRepository.findById(id);
    }

    @Override
    public void deleteDetalleCarrito(Long id) {
        detalleCarritoRepository.deleteById(id);
    }

    @Override
    public void deleteAllByCarritoId(Long carritoId) {
        detalleCarritoRepository.deleteAllByCarritoId(carritoId);
    }

}
