package com.durandsuppicich.danmsmateriales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.durandsuppicich.danmsmateriales.domain.Material;

import org.springframework.stereotype.Service;

@Service
public class ServicioMaterial implements IServicioMaterial {

    private Integer ID_GEN = 1;
    private List<Material> materiales = new ArrayList<Material>();

    @Override
    public Material crear(Material material) {
        material.setId(ID_GEN++);
        materiales.add(material);
        return material;
    }

    @Override
    public List<Material> todos() {
        return materiales;
    }

    @Override
    public Optional<Material> materialPorId(Integer id) {
        Optional<Material> optMaterial = materiales
            .stream()
            .filter(m -> m.getId().equals(id))
            .findFirst();
        return optMaterial;
    }

    @Override
    public Optional<Material> materialPorNombre(String nombre) {
        Optional<Material> optMaterial = materiales
            .stream()
            .filter(m -> m.getNombre().equals(nombre))
            .findFirst();
        return optMaterial;
    }

    @Override
    public void actualizar(Integer id, Material material) {
        OptionalInt indexOpt = IntStream.range(0, materiales.size())
            .filter(i -> materiales.get(i).getId().equals(id))
            .findFirst();

        if (indexOpt.isPresent()) {
            materiales.set(indexOpt.getAsInt(), material);
        }
        // Lanzar excepeción
    }

    @Override
    public void eliminar(Integer id) {
        OptionalInt indexOpt = IntStream.range(0, materiales.size())
            .filter(i -> materiales.get(i).getId().equals(id))
            .findFirst();
    
        if (indexOpt.isPresent()) {
            materiales.remove(indexOpt.getAsInt());
        }
        // Lanzar excepeción
    }
}