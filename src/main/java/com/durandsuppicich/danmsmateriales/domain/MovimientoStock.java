package com.durandsuppicich.danmsmateriales.domain;

import java.time.Instant;

public class MovimientoStock {

    private Integer id; 
    private DetallePedido detallePedido; 
    private DetalleProvision detalleProvision;
    private Material material;
    private Integer cantidadEntrada; 
    private Integer cantidadSalida;
    private Instant fecha;
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public DetallePedido getDetallePedido() {
        return detallePedido;
    }
    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }
    public DetalleProvision getDetalleProvision() {
        return detalleProvision;
    }
    public void setDetalleProvision(DetalleProvision detalleProvision) {
        this.detalleProvision = detalleProvision;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public Integer getCantidadEntrada() {
        return cantidadEntrada;
    }
    public void setCantidadEntrada(Integer cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }
    public Integer getCantidadSalida() {
        return cantidadSalida;
    }
    public void setCantidadSalida(Integer cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }
    public Instant getFecha() {
        return fecha;
    }
    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MovimientoStock other = (MovimientoStock) obj;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    } 
}
