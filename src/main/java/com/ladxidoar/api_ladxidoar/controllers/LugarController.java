package com.ladxidoar.api_ladxidoar.controllers;

import com.ladxidoar.api_ladxidoar.entities.Lugar;
import com.ladxidoar.api_ladxidoar.services.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/lugares")
public class LugarController {

    private final LugarService lugarService;

    @Autowired
    public LugarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @PostMapping
    public ResponseEntity<Lugar> createLugar(@RequestPart("lugar") Lugar lugar, @RequestPart("imagen")MultipartFile imagen) {
        Lugar nuevoLugar = lugarService.saveLugar(lugar, imagen);
        System.out.println(nuevoLugar);
        return ResponseEntity.ok(nuevoLugar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lugar> updateLugar(
            @PathVariable Long id,
            @RequestPart Lugar lugar,
            @RequestPart(required = false) MultipartFile imagen) {
        Lugar lugarActualizado = lugarService.updateLugar(id, lugar, imagen);
        return ResponseEntity.ok(lugarActualizado);
    }

    @GetMapping
    public ResponseEntity<List<Lugar>> getAllLugares() {
        List<Lugar> lugares = lugarService.getLugares();
        return ResponseEntity.ok(lugares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lugar> getLugarById(@PathVariable Long id) {
        return lugarService.getLugarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Lugar>> getLugarById(@PathVariable String categoria) {
        List<Lugar> lugares = lugarService.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(lugares);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLugar(@PathVariable Long id) {
        lugarService.deleteLugar(id);
        return ResponseEntity.noContent().build();
    }

}
