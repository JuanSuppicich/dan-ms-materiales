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
@Table(name = "provision", schema = "ms_products")
public class Provision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provision_id")
    private Integer id;

    @Column(name = "provision_date")
    private Instant provisionDate;

    @OneToMany(mappedBy = "provision", cascade = CascadeType.PERSIST)
    private  List<ProvisionItem> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionDate(Instant provisionDate) {
        this.provisionDate = provisionDate;
    }

    public List<ProvisionItem> getItems() {
        return items;
    }

    public void setItems(List<ProvisionItem> items) {
        this.items = items;
    }

    public void addProvisionItem(ProvisionItem provisionItem) {
        this.items.add(provisionItem);
        provisionItem.setProvision(this);
    }

    @Override
    public String toString() {
        return "Provision{" +
                "id=" + id +
                ", provisionDate=" + provisionDate +
                ", items=" + items +
                '}';
    }
}
