package com.durandsuppicich.danmsmateriales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import com.durandsuppicich.danmsmateriales.dao.DetallePedidoJpaRepository;
import com.durandsuppicich.danmsmateriales.dao.MaterialJpaRepository;
import com.durandsuppicich.danmsmateriales.domain.DetallePedido;
import com.durandsuppicich.danmsmateriales.domain.Material;
import com.durandsuppicich.danmsmateriales.exception.NotFoundException;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ServicioMaterial implements IServicioMaterial {

    private final MaterialJpaRepository materialRepository;
    private final IServicioMovimientoStock servicioMovimientoStock;
    private final DetallePedidoJpaRepository detallePedidoRepository;
    private final IServicioProvision servicioProvision;

    public ServicioMaterial(
            MaterialJpaRepository materialRepository, 
            IServicioMovimientoStock servicioMovimientoStock, 
            DetallePedidoJpaRepository detallePedidoRepository,
            IServicioProvision servicioProvision) {

        this.materialRepository = materialRepository;
        this.servicioMovimientoStock = servicioMovimientoStock;
        this.detallePedidoRepository = detallePedidoRepository;
        this.servicioProvision = servicioProvision;
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
        return materialRepository.findByNombre(nombre);
    }

    @Override
    public List<Material> materialesPorRangoStock(Integer stockMinimo, Integer stockMaximo) {
        return materialRepository.findByStockActualBetween(stockMinimo, stockMaximo);
    }

    @Override
    public List<Material> materialesPorRangoPrecio(Double precioMinimo, Double precioMaximo) {
        return materialRepository.findByPrecioBetween(precioMinimo, precioMaximo);
    }

    @Override
    public void actualizar(Integer id, Material material) {

        if (materialRepository.existsById(id)) {
            materialRepository.save(material);
        }
        else {
            throw new NotFoundException("Material inexistente. Id: " + id);
        }
    }

    @Override
    public void eliminar(Integer id) {

        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Material inexistente. Id: " + id);
        }
    }

    @JmsListener(destination = "COLA_PEDIDOS")
    public void handle(ObjectMessage message) {

        try {
            Integer idPedido = (Integer) message.getObject();
            List<DetallePedido> detalles = detallePedidoRepository.findAllByIdPedido(idPedido);
            servicioMovimientoStock.generarMovimiento(detalles);
            this.actualizarStock(detalles);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void actualizarStock(List<DetallePedido> detalles) {

        List<Material> materiales = new ArrayList<Material>();

        for(DetallePedido dp: detalles) {

            Material material = dp.getMaterial();

            Integer nuevoStock = material.getStockActual() - dp.getCantidad();
            material.setStockActual(nuevoStock);

            if (nuevoStock <= material.getStockMinimo()) {

                materiales.add(material);
            
            }

            materialRepository.save(material);
        }

        if (! materiales.isEmpty()) {
            servicioProvision.generarOrdenProvision(materiales);
        }
    }
}