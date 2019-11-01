package com.hdd.repository;

import com.hdd.model.Customer;
import com.hdd.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Iterable<Customer> findAllByOrder(Order order);
}
