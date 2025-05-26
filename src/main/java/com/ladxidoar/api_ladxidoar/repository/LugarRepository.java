package com.ladxidoar.api_ladxidoar.repository;

import com.ladxidoar.api_ladxidoar.entities.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LugarRepository extends JpaRepository <Lugar, Long> {

    List<Lugar> findByMunicipioIgnoreCase(String municipio);

    List<Lugar> findByCategoria(String categoria);

}
