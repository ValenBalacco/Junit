package com.ElBuenSabor;

import com.ElBuenSabor.entities.Articulo;
import com.ElBuenSabor.repositories.ArticuloRepositorio;
import com.ElBuenSabor.services.ServicioDeCompra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioDeCompraTest {
    @Mock
    private ArticuloRepositorio articuloRepositorio;

    @InjectMocks
    private ServicioDeCompra servicioDeCompra;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarArticulo() {
        Articulo articulo = new Articulo("Producto 1", 100.0);
        when(articuloRepositorio.save(articulo)).thenReturn(articulo);
        Articulo articuloGuardado = servicioDeCompra.guardarArticulo(articulo);
        assertThat(articuloGuardado.getNombre()).isEqualTo("Producto 1");
        assertThat(articuloGuardado.getPrecio()).isEqualTo(100.0);
        verify(articuloRepositorio).save(articulo);
    }

    @Test
    public void testObtenerArticuloPorId() {
        Articulo articulo = new Articulo("Producto 1", 100.0);
        when(articuloRepositorio.findById(1L)).thenReturn(Optional.of(articulo));
        Articulo articuloRecuperado = servicioDeCompra.obtenerArticuloPorId(1L);
        assertThat(articuloRecuperado).isNotNull();
        assertThat(articuloRecuperado.getNombre()).isEqualTo("Producto 1");
        assertThat(articuloRecuperado.getPrecio()).isEqualTo(100.0);
        verify(articuloRepositorio).findById(1L);
    }

    @Test
    public void testObtenerTodosLosArticulos() {
        Articulo articulo1 = new Articulo("Producto 1", 100.0);
        Articulo articulo2 = new Articulo("Producto 2", 200.0);
        when(articuloRepositorio.findAll()).thenReturn(Arrays.asList(articulo1, articulo2));
        List<Articulo> articulos = servicioDeCompra.obtenerTodosLosArticulos();
        assertThat(articulos).hasSize(2);
        assertThat(articulos).extracting(Articulo::getNombre).contains("Producto 1", "Producto 2");
        verify(articuloRepositorio).findAll();
    }

    @Test
    public void testEliminarArticuloPorId() {
        doNothing().when(articuloRepositorio).deleteById(1L);
        servicioDeCompra.eliminarArticuloPorId(1L);
        verify(articuloRepositorio).deleteById(1L);
    }
}

