package com.durandsuppicich.danmsmateriales.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.DanMsMaterialesApplicationTests;
import com.durandsuppicich.danmsmateriales.domain.Material;
import com.durandsuppicich.danmsmateriales.domain.Unidad;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = DanMsMaterialesApplicationTests.class)
@Profile("testing")
public class MaterialJpaRepositoryTest {

    @Autowired
    public MaterialJpaRepository materialRepository;

    @Test
    public void elRepositorioExiste() {
        assertNotNull(materialRepository);
    }

    @Test
    @Sql(scripts = {"/datos_test.sql"})
    public void findAll_materialesAlmacenados_materialesRecuperadosConUnidad() {

        List<Material> materiales = materialRepository.findAll();

        assertFalse(materiales.isEmpty());
        assertEquals(2, materiales.size());
    }

    @Test
    public void findById_materialAlmacenado_materialRecuperadoConUnidad() {

        Optional<Material> material = materialRepository.findById(1);

        assertTrue(material.isPresent());
        assertEquals(1, material.get().getId());
        assertNotNull(material.get().getUnidad());
        assertEquals("Kilos", material.get().getUnidad().getDescripcion());
    }

    @Test
    public void findByNombreContaining_materialesAlmacenados_materialesConNombreRecuperados() {

        Optional<Material> material01 = materialRepository.findByNombre("Material01");
        Optional<Material> material02 = materialRepository.findByNombre("Material99");

        assertTrue(material01.isPresent());
        assertEquals("Material01", material01.get().getNombre());

        assertFalse(material02.isPresent());
    }

    @Test
    public void save_materialOk_materialCreado() {

        Unidad unidad = new Unidad(3, "Litros");

        Material material = new Material("Material03", "Descripcion03", 33.0, 333, 33, unidad);

        Material resultado = materialRepository.save(material);

        assertNotNull(resultado);
        assertNotNull(resultado.getUnidad());
        assertEquals("Litros", resultado.getUnidad().getDescripcion());
        assertEquals(3, materialRepository.count());
    }
}