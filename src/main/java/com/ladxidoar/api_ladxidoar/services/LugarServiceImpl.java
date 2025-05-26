package com.ladxidoar.api_ladxidoar.services;

import com.ladxidoar.api_ladxidoar.entities.Lugar;
import com.ladxidoar.api_ladxidoar.repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LugarServiceImpl implements LugarService{

    private final LugarRepository lugarRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public LugarServiceImpl(LugarRepository lugarRepository, CloudinaryService cloudinaryService) {
        this.lugarRepository = lugarRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Lugar saveLugar(Lugar lugar, MultipartFile imagen) {
        if (imagen != null && !imagen.isEmpty()) {
            String urlImagen = cloudinaryService.uploadFile(imagen);
            lugar.setUrlImagen(urlImagen);
        }
        return lugarRepository.save(lugar);
    }

    @Override
    public Lugar updateLugar(Long id, Lugar lugarActualizado, MultipartFile nuevaImagen) {
        return lugarRepository.findById(id)
                .map(lugarExistente -> {
                    lugarExistente.setNombre(lugarActualizado.getNombre());
                    lugarExistente.setDescripcion(lugarActualizado.getDescripcion());

                    if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
                        // Eliminar la imagen anterior si existe
                        if (lugarExistente.getUrlImagen() != null) {
                            cloudinaryService.deleteFile(lugarExistente.getUrlImagen());
                        }
                        // Subir la nueva imagen
                        String nuevaImagenUrl = cloudinaryService.uploadFile(nuevaImagen);
                        lugarExistente.setUrlImagen(nuevaImagenUrl);
                    }

                    return lugarRepository.save(lugarExistente);
                })
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado con id: " + id));
    }

    @Override
    public List<Lugar> getLugares(){
        return lugarRepository.findAll();
    }

    @Override
    public List<Lugar> obtenerPorMunicipioInsensible(String municipio){
        return lugarRepository.findByMunicipioIgnoreCase(municipio);
    }

    @Override
    public List<Lugar> obtenerPorCategoria(String categoria){
        return lugarRepository.findByCategoria(categoria);
    }

    @Override
    public Optional<Lugar> getLugarById(Long id){
        return lugarRepository.findById(id);
    }

    @Override
    public void deleteLugar(Long id){
        lugarRepository.deleteById(id);
    }

}
