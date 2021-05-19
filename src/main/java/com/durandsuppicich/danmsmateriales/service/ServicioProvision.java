package com.durandsuppicich.danmsmateriales.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.durandsuppicich.danmsmateriales.dao.ProvisionJpaRepository;
import com.durandsuppicich.danmsmateriales.domain.DetalleProvision;
import com.durandsuppicich.danmsmateriales.domain.Material;
import com.durandsuppicich.danmsmateriales.domain.Provision;

import org.springframework.stereotype.Service;

@Service
public class ServicioProvision implements IServicioProvision {

    private final ProvisionJpaRepository provisionRepository;

    public ServicioProvision(ProvisionJpaRepository provisionRepository) {
        this.provisionRepository = provisionRepository;
    }

    @Override
    public void generarOrdenProvision(List<Material> materiales) {
        //No deberia generar una orden de provision si ya hay una orden pendiente para ese producto.. 
        Provision provision = new Provision();
        provision.setFechaProvision(Instant.now().truncatedTo(ChronoUnit.DAYS).plus(7L, ChronoUnit.DAYS)); //lead time?
        
        for (Material m: materiales) {

            DetalleProvision detalleProvision = new DetalleProvision();
            detalleProvision.setMaterial(m);
            detalleProvision.setCantidad(4 * m.getStockMinimo()); //Cantidad provision? 
            provision.addDetalle(detalleProvision);
        }

        provisionRepository.save(provision);
    }   
}
