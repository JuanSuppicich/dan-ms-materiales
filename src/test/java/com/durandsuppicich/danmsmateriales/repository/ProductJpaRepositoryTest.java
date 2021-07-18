package com.durandsuppicich.danmsmateriales.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.DanMsMaterialesApplicationTests;
import com.durandsuppicich.danmsmateriales.domain.Product;
import com.durandsuppicich.danmsmateriales.domain.Unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = DanMsMaterialesApplicationTests.class)
@Profile("testing")
public class ProductJpaRepositoryTest {

    @Autowired
    public IProductJpaRepository productRepository;

    @Test
    public void repositoryExists() {
        assertNotNull(productRepository);
    }

    @Test
    @Sql(scripts = {"/datos_test.sql"})
    public void findAll_materialesAlmacenados_materialesRecuperadosConUnidad() {

        List<Product> materiales = productRepository.findAll();

        assertFalse(materiales.isEmpty());
        assertEquals(2, materiales.size());
    }

    @Test
    public void findById_materialAlmacenado_materialRecuperadoConUnidad() {

        Optional<Product> material = productRepository.findById(1);

        assertTrue(material.isPresent());
        assertEquals(1, material.get().getId());
        assertNotNull(material.get().getUnit());
        assertEquals("Kilos", material.get().getUnit().getDescription());
    }

    @Test
    public void findByNombreContaining_materialesAlmacenados_materialesConNombreRecuperados() {

        Optional<Product> material01 = productRepository.findByName("Material01");
        Optional<Product> material02 = productRepository.findByName("Material99");

        assertTrue(material01.isPresent());
        assertEquals("Material01", material01.get().getName());

        assertFalse(material02.isPresent());
    }

    @Test
    public void save_materialOk_materialCreado() {

        Unit unit = new Unit();
        unit.setId(3);
        unit.setDescription("Litros");

        Product product = new Product();

        product.setName("Material03");
        product.setDescription("Descripcion03");
        product.setPrice(33.0);
        product.setCurrentStock(333);
        product.setMinimumStock(33);
        product.setUnit(unit);

        Product resultado = productRepository.save(product);

        assertNotNull(resultado);
        assertNotNull(resultado.getUnit());
        assertEquals("Litros", resultado.getUnit().getDescription());
        assertEquals(3, productRepository.count());
    }
}
