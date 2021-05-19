package com.durandsuppicich.danmsmateriales.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsmateriales.domain.Material;

public interface IServicioMaterial {

    Material crear(Material material);
    List<Material> todos();
    Optional<Material> materialPorId(Integer id);
    Optional<Material> materialPorNombre(String nombre);
    List<Material> materialesPorRangoStock(Integer stockMinimo, Integer stockMaximo);
    List<Material> materialesPorRangoPrecio(Double precioMinimo, Double precioMaximo);
    void actualizar(Integer id, Material material);
    void eliminar(Integer id);
}