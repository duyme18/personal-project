package com.hdd.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String gender;
    private String address;
    private int phone;
    private String avatar;

    public Employee() {
    }

    public Employee(String name, Date birthday, String gender, String address, int phone, String avatar) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Employee(Long id, String name, Date birthday, String gender, String address, int phone, String avatar) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
