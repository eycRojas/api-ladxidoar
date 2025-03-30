package com.ladxidoar.api_ladxidoar.repository;

import com.ladxidoar.api_ladxidoar.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndContrasenia(String email, String contrasenia);

}
