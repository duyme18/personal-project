package com.hdd.service;

import com.hdd.model.Customer;
import com.hdd.model.Order;

public interface CustomerService {
    Iterable<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);

    Iterable<Customer> findAllByOrder(Order order);
}
