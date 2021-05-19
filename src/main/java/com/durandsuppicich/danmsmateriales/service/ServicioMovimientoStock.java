package com.durandsuppicich.danmsmateriales.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.durandsuppicich.danmsmateriales.dao.MovimientoStockJpaRepository;
import com.durandsuppicich.danmsmateriales.domain.DetallePedido;
import com.durandsuppicich.danmsmateriales.domain.MovimientoStock;

import org.springframework.stereotype.Service;

@Service
public class ServicioMovimientoStock implements IServicioMovimientoStock {

    private final MovimientoStockJpaRepository movimientoStockRepository;

    public ServicioMovimientoStock(MovimientoStockJpaRepository movimientoStockRepository) {
        this.movimientoStockRepository = movimientoStockRepository;
    }

    @Override
    public void generarMovimiento(List<DetallePedido> detalles) {
        
        for (DetallePedido dp: detalles) {

            MovimientoStock movimientoStock = new MovimientoStock();

            movimientoStock.setDetallePedido(dp);
            movimientoStock.setMaterial(dp.getMaterial());
            movimientoStock.setCantidadSalida(dp.getCantidad());
            movimientoStock.setFecha(Instant.now().truncatedTo(ChronoUnit.MILLIS));
            
            movimientoStockRepository.save(movimientoStock);
        }
    }
}
