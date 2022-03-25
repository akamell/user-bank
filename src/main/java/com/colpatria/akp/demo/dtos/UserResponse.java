package com.colpatria.akp.demo.dtos;

import java.util.Date;

public class UserResponse {
    private int id;
    private String username;
    private String name;
    private String lastname;
    private Date createdAt;
    private Date updatedAt;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public UserResponse(int id, String username, String name, String lastname, Date createdAt, Date updatedAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
