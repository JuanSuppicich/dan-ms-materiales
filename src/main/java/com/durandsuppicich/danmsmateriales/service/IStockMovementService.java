package com.durandsuppicich.danmsmateriales.service;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.OrderItem;

public interface IStockMovementService {

    void generateStockMovement(List<OrderItem> items);
}
