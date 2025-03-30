package com.ladxidoar.api_ladxidoar.services;

import com.ladxidoar.api_ladxidoar.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    Usuario updateUsuario(Usuario usuario);

    List<Usuario> getUsuarios();

    Optional<Usuario> getUsuarioById(Long id);

    void deleteUsuario(Long id);

    Optional<Usuario> getUsuarioByEmail(String email);

    Optional<Usuario> getUsuarioByEmailAndContrasenia(String email, String contrasenia);

    //UsuarioDTO convertToDTO(Usuario usuario);

}
