package com.durandsuppicich.danmsmateriales.domain;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROVISION", schema = "MS_MATERIALES")
public class Provision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVISION")
    private Integer id;

    private Instant fechaProvision;

    @OneToMany(mappedBy = "provision", cascade = CascadeType.PERSIST)
    private  List<DetalleProvision> detalleProvision;

    public Provision() { }

    public Provision(Instant fechaProvision, List<DetalleProvision> detalleProvision) {
        this.fechaProvision = fechaProvision;
        this.detalleProvision = detalleProvision;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaProvision() {
        return fechaProvision;
    }

    public void setFechaProvision(Instant fechaProvision) {
        this.fechaProvision = fechaProvision;
    }

    public List<DetalleProvision> getDetalleProvision() {
        return detalleProvision;
    }

    public void setDetalleProvision(List<DetalleProvision> detalleProvision) {
        this.detalleProvision = detalleProvision;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fechaProvision == null) ? 0 : fechaProvision.hashCode());
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
        Provision other = (Provision) obj;
        if (fechaProvision == null) {
            if (other.fechaProvision != null)
                return false;
        } else if (!fechaProvision.equals(other.fechaProvision))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    } 
}