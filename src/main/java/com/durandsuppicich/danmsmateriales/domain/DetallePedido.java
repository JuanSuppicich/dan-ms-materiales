package com.durandsuppicich.danmsmateriales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Tiene que ser READ ONLY
@Entity
@Table(name = "DETALLE_PEDIDO", schema = "MS_PEDIDOS")
public class DetallePedido {

    @Id
    @Column(name = "ID_DETALLE_PEDIDO")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_MATERIAL")
    private Material material;

    @Column(nullable = false)
    private Integer cantidad;

    public DetallePedido() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetallePedido [cantidad=" + cantidad + ", id=" + id + ", material=" + material + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        DetallePedido other = (DetallePedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }    
}