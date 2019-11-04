package com.hdd.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayCreate;
    @OneToMany(mappedBy = "order")
    private Set<Customer> customerSet;

    public Order() {
    }

    public Order(String name, Date checkin, Date checkout, Date dayCreate, Set<Customer> customerSet) {
        this.name = name;
        this.checkin = checkin;
        this.checkout = checkout;
        this.dayCreate = dayCreate;
        this.customerSet = customerSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Date getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(Date dayCreate) {
        this.dayCreate = dayCreate;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }
}
