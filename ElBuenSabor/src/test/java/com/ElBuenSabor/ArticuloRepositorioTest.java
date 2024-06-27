package com.ElBuenSabor;

import com.ElBuenSabor.entities.Articulo;
import com.ElBuenSabor.repositories.ArticuloRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ArticuloRepositorioTest {
    @Autowired
    private ArticuloRepositorio articuloRepositorio;

    @Test
    public void testGuardarYRecuperarArticulo() {
        Articulo articulo = new Articulo("Producto 1", 100.0);
        Articulo articuloGuardado = articuloRepositorio.save(articulo);
        assertThat(articuloGuardado.getId()).isNotNull();
        Articulo articuloRecuperado = articuloRepositorio.findById(articuloGuardado.getId()).orElse(null);
        assertThat(articuloRecuperado).isNotNull();
        assertThat(articuloRecuperado.getNombre()).isEqualTo("Producto 1");
        assertThat(articuloRecuperado.getPrecio()).isEqualTo(100.0);
    }

    @Test
    public void testEncontrarTodosLosArticulos() {
        Articulo articulo1 = new Articulo("Producto 1", 100.0);
        Articulo articulo2 = new Articulo("Producto 2", 200.0);
        articuloRepositorio.save(articulo1);
        articuloRepositorio.save(articulo2);
        List<Articulo> articulos = articuloRepositorio.findAll();
        assertThat(articulos).hasSize(2);
        assertThat(articulos).extracting(Articulo::getNombre).contains("Producto 1", "Producto 2");
    }

    @Test
    public void testActualizarArticulo() {
        Articulo articulo = new Articulo("Producto 1", 100.0);
        Articulo articuloGuardado = articuloRepositorio.save(articulo);
        articuloGuardado.setNombre("Producto 1 Actualizado");
        articuloGuardado.setPrecio(150.0);
        Articulo articuloActualizado = articuloRepositorio.save(articuloGuardado);
        assertThat(articuloActualizado.getNombre()).isEqualTo("Producto 1 Actualizado");
        assertThat(articuloActualizado.getPrecio()).isEqualTo(150.0);
    }

    @Test
    public void testEliminarArticulo() {
        Articulo articulo = new Articulo("Producto 1", 100.0);
        Articulo articuloGuardado = articuloRepositorio.save(articulo);
        articuloRepositorio.deleteById(articuloGuardado.getId());
        Articulo articuloEliminado = articuloRepositorio.findById(articuloGuardado.getId()).orElse(null);
        assertThat(articuloEliminado).isNull();
    }
}
