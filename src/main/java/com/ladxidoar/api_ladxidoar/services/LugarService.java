package com.ladxidoar.api_ladxidoar.services;

import com.ladxidoar.api_ladxidoar.entities.Lugar;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface LugarService {

    Lugar saveLugar(Lugar lugar, MultipartFile imagen);

    Lugar updateLugar(Long id, Lugar lugar, MultipartFile imagen);

    List<Lugar> getLugares();

    List<Lugar> obtenerPorMunicipioInsensible(String municipio);

    List<Lugar> obtenerPorCategoria(String municipio);

    Optional<Lugar> getLugarById(Long id);

    void deleteLugar(Long id);

}
