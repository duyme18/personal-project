package com.hdd.service;

import com.hdd.model.Customer;
import com.hdd.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);

    Iterable<Customer> findAllByOrder(Order order);

    Page<Customer> findAllByNameContaining(String name, Pageable pageable);

}
