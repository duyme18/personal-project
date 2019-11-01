package com.hdd.service;

import com.hdd.model.Order;

public interface OrderService {
    Iterable<Order> findAll();

    Order findById(Long id);

    void save(Order order);

    void remove(Long id);
}
