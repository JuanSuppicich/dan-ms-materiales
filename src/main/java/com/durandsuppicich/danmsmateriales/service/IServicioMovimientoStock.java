package com.durandsuppicich.danmsmateriales.service;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.DetallePedido;

public interface IServicioMovimientoStock {

    void generarMovimiento(List<DetallePedido> detalles);
    
}
