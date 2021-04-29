package com.durandsuppicich.danmsmateriales.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.dao.MaterialJpaRepository;
import com.durandsuppicich.danmsmateriales.domain.Material;

import org.springframework.stereotype.Service;

@Service
public class ServicioMaterial implements IServicioMaterial {

    private final MaterialJpaRepository materialRepository;

    public ServicioMaterial(MaterialJpaRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material crear(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public List<Material> todos() {
        return materialRepository.findAll();
    }

    @Override
    public Optional<Material> materialPorId(Integer id) {
        return materialRepository.findById(id);
    }

    @Override
    public Optional<Material> materialPorNombre(String nombre) {
        // Completar
        return null;
    }

    @Override
    public void actualizar(Integer id, Material material) {
        if (materialRepository.existsById(id)) {
            materialRepository.save(material);
        }
        // Lanzar excepción
    }

    @Override
    public void eliminar(Integer id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
        }
        // Lanzar excepción
    }
}