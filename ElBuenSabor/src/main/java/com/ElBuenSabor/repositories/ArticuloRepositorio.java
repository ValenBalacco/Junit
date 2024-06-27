package com.ElBuenSabor.repositories;

import com.ElBuenSabor.entities.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloRepositorio extends JpaRepository<Articulo, Long> {
    List<Articulo> findByNombre(String nombre);
}
