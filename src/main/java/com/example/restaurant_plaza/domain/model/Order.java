package com.example.restaurant_plaza.domain.model;

import java.util.Date;

public class Order {

    private Long id;
    private Long clientId;
    private Date createdAt;
    private String status;
    private Long chefId;
    private Long restaurantId;

    public Order(Long id, Long clientId, Date createdAt, String status, Long chefId, Long restaurantId) {
        this.id = id;
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.status = status;
        this.chefId = chefId;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return createdAt;
    }

    public void setDate(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getChefId() {
        return chefId;
    }

    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
