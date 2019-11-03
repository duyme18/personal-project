package com.hdd.model;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeForm {
    private Long id;
    private String name;
    private String birthday;
    private String gender;
    private String address;
    private int phone;
    private MultipartFile avatar;

    public EmployeeForm() {
    }

    public EmployeeForm(String name, String birthday, String gender, String address, int phone, MultipartFile avatar) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
    }

    public EmployeeForm(Long id, String name, String birthday, String gender, String address, int phone, MultipartFile avatar) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
