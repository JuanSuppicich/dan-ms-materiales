package com.durandsuppicich.danmsmateriales.service;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.Material;

public interface IServicioProvision {

    void generarOrdenProvision(List<Material> materiales);
    
}
