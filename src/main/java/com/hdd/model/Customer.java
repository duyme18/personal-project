package com.hdd.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String gender;
    private int phone;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Customer() {
    }

    public Customer(String name, Date birthday, String gender, int phone, Order order) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.order = order;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d,name='%s',birthday='%s',gender='%s',phone=%d]", id, birthday, gender, phone);
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
