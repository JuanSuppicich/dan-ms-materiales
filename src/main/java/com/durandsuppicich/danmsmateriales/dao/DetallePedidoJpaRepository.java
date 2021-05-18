package com.durandsuppicich.danmsmateriales.dao;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.DetallePedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoJpaRepository extends JpaRepository<DetallePedido, Integer> {

    //@Query(value = "SELECT * FROM ms_pedidos.detalle_pedido dp JOIN ms_pedidos.pedido p ON p.id_pedido = dp.id_pedido WHERE dp.id_pedido = :id", nativeQuery = true)
    @Query(value = "SELECT * FROM ms_pedidos.detalle_pedido dp WHERE dp.id_pedido = :id", nativeQuery = true)
    List<DetallePedido> findAllByIdPedido(Integer id);

 }