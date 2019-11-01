package com.hdd.model;

import org.springframework.web.multipart.MultipartFile;

public class RoomForm {
    private Long id;
    private String name;
    private double price;
    private String status;
    private MultipartFile image;

    public RoomForm() {
    }

    public RoomForm(String name, double price, String status, MultipartFile image) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public RoomForm(Long id, String name, double price, String status, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
