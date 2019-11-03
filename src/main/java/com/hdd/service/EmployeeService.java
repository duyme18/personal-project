package com.hdd.service;

import com.hdd.model.Employee;

public interface EmployeeService {
    Iterable<Employee> findAll();

    Employee findById(Long id);

    void save(Employee employee);

    void remove(Long id);
}
