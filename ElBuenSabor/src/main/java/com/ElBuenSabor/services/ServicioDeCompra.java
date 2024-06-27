package com.ElBuenSabor.services;


import com.ElBuenSabor.entities.Articulo;
import com.ElBuenSabor.repositories.ArticuloRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDeCompra {
    private final ArticuloRepositorio articuloRepositorio;

    @Autowired
    public ServicioDeCompra(ArticuloRepositorio articuloRepositorio) {
        this.articuloRepositorio = articuloRepositorio;
    }

    public Articulo guardarArticulo(Articulo articulo) {
        return articuloRepositorio.save(articulo);
    }

    public Articulo obtenerArticuloPorId(Long id) {
        return articuloRepositorio.findById(id).orElse(null);
    }

    public List<Articulo> obtenerTodosLosArticulos() {
        return articuloRepositorio.findAll();
    }

    public void eliminarArticuloPorId(Long id) {
        articuloRepositorio.deleteById(id);
    }
}

