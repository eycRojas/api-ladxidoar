package com.ladxidoar.api_ladxidoar.controllers;

import com.ladxidoar.api_ladxidoar.dtos.UsuarioDTO;
import com.ladxidoar.api_ladxidoar.entities.Usuario;
import com.ladxidoar.api_ladxidoar.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setId(savedUsuario.getId());
            usuarioDto.setNombres(savedUsuario.getNombres());
            usuarioDto.setApellidos(savedUsuario.getApellidos());
            usuarioDto.setEmail(savedUsuario.getEmail());
            usuarioDto.setContrasenia(savedUsuario.getContrasenia());
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.updateUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {

        List<Usuario> usuarios = usuarioService.getUsuarios();
        //List<UsuarioDTO> usuarioDTOS = usuarios.stream().map(usuarioService::convertToDTO).toList();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setId(usuario.get().getId());
            usuarioDto.setNombres(usuario.get().getNombres());
            usuarioDto.setApellidos(usuario.get().getApellidos());
            usuarioDto.setEmail(usuario.get().getEmail());
            usuarioDto.setContrasenia(usuario.get().getContrasenia());
            if(usuario.get().getCarrito()!=null){
                usuarioDto.setCarritoId(usuario.get().getCarrito().getId());
            }
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByEmail(email);
        if (usuario.isPresent()) {
            System.out.println("DATOS: "+ email);
            //UsuarioDTO usuarioDTO = usuarioService.convertToDTO(usuario.get());
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}/{contrasenia}")
    public ResponseEntity<UsuarioDTO> getUsuarioByEmailAndContrasenia(@PathVariable String email, @PathVariable String contrasenia) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByEmailAndContrasenia(email, contrasenia);
        if (usuario.isPresent()) {
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setId(usuario.get().getId());
            usuarioDto.setNombres(usuario.get().getNombres());
            usuarioDto.setApellidos(usuario.get().getApellidos());
            usuarioDto.setEmail(usuario.get().getEmail());
            usuarioDto.setContrasenia(usuario.get().getContrasenia());
            if(usuario.get().getCarrito()!=null){
                usuarioDto.setCarritoId(usuario.get().getCarrito().getId());
            }
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (usuario.isPresent()) {
            usuarioService.deleteUsuario(usuario.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
