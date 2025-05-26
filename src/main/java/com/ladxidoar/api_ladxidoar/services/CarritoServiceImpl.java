package com.ladxidoar.api_ladxidoar.services;

import com.ladxidoar.api_ladxidoar.entities.Carrito;
import com.ladxidoar.api_ladxidoar.entities.Usuario;
import com.ladxidoar.api_ladxidoar.repository.CarritoRepository;
import com.ladxidoar.api_ladxidoar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService{

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

     @Override
    public Carrito saveCarrito(Carrito carrito){
         return carritoRepository.save(carrito);
     }

    @Override
    public List<Carrito> getCarritos(){
         return carritoRepository.findAll();
    }

    @Override
    public Optional<Carrito> getCarritoByUsuarioId(Long usuarioId){
         return carritoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    @Transactional
    public void vaciarCarrito(Long carritoId){
         carritoRepository.vaciarCarrito(carritoId);
     }
}
