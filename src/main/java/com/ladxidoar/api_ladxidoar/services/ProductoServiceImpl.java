package com.ladxidoar.api_ladxidoar.services;


import com.ladxidoar.api_ladxidoar.dtos.ProductoCarritoDTO;
import com.ladxidoar.api_ladxidoar.dtos.ProductoDTO;
import com.ladxidoar.api_ladxidoar.entities.DetalleCarrito;
import com.ladxidoar.api_ladxidoar.entities.Producto;
import com.ladxidoar.api_ladxidoar.repository.LugarRepository;
import com.ladxidoar.api_ladxidoar.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, CloudinaryService cloudinaryService) {
        this.productoRepository = productoRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Producto saveProducto(Producto producto, MultipartFile imagen){
        if (imagen != null && !imagen.isEmpty()) {
            String urlImagen = cloudinaryService.uploadFile(imagen);
            producto.setUrlImagen(urlImagen);
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto){
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> getProductoById(Long id){
        return productoRepository.findById(id);
    }

    @Override
    public List<ProductoDTO> getProductos(){
        // 1. Obtener los productos
        List<Producto> productos = productoRepository.findAll();

        // 2. Transformar a ProductoDTO
        return productos.stream()
                .map(producto -> {
                    ProductoDTO dto = new ProductoDTO();

                    // Mapear campos básicos del producto
                    dto.setId(producto.getId());
                    dto.setNombre(producto.getNombre());
                    dto.setDescripcion(producto.getDescripcion());
                    dto.setPrecio(producto.getPrecio());
                    dto.setUrlImagen(producto.getUrlImagen());
                    dto.setCantidad(producto.getCantidad());
                    dto.setAutor(producto.getAutor());
                    dto.setCategoria(producto.getCategoria());


                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleCarrito> findDetallesConProductosByUsuarioId(Long usuarioId){
        return  productoRepository.findDetallesConProductosByUsuarioId(usuarioId);
    }

    @Override
    public List<ProductoCarritoDTO> obtenerProductosEnCarrito(Long usuarioId) {
        // 1. Obtener los detalles del carrito con los productos cargados
        List<DetalleCarrito> detalles = productoRepository
                .findDetallesConProductosByUsuarioId(usuarioId);

        // 2. Transformar a ProductoDTO
        return detalles.stream()
                .map(detalle -> {
                    Producto producto = detalle.getProducto();
                    ProductoCarritoDTO dto = new ProductoCarritoDTO();

                    // Mapear campos básicos del producto
                    dto.setId(producto.getId());
                    dto.setNombre(producto.getNombre());
                    dto.setDescripcion(producto.getDescripcion());
                    dto.setPrecio(producto.getPrecio());
                    dto.setUrlImagen(producto.getUrlImagen());
                    dto.setAutor(producto.getAutor());
                    dto.setCategoria(producto.getCategoria());
                    dto.setCantidadStock(producto.getCantidad());
                    dto.setCantidad(detalle.getCantidad());
                    dto.setDetalleId(detalle.getId());

                    return dto;
                })
                .collect(Collectors.toList());
    }

}
