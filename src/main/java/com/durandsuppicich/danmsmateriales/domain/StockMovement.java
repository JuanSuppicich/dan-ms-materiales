package com.durandsuppicich.danmsmateriales.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock_movement", schema = "ms_products")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_movement_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @OneToOne
    @JoinColumn(name = "provision_item_id")
    private ProvisionItem provisionItem;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Column(name = "movement_date", nullable = false)
    private Instant movementDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public ProvisionItem getProvisionItem() {
        return provisionItem;
    }

    public void setProvisionItem(ProvisionItem provisionItem) {
        this.provisionItem = provisionItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Instant getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Instant movementDate) {
        this.movementDate = movementDate;
    }

    @Override
    public String toString() {
        return "StockMovement{" +
                "id=" + id +
                ", orderItem=" + orderItem +
                ", provisionItem=" + provisionItem +
                ", product=" + product +
                ", quantity=" + quantity +
                ", movementDate=" + movementDate +
                '}';
    }
}