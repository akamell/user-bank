package com.colpatria.akp.demo.repositories;

import java.util.List;

import com.colpatria.akp.demo.dtos.UserResponse;
import com.colpatria.akp.demo.entities.User;

public interface IUserRepository {
    public List<User> findAll();
    public UserResponse findById(int id);
    public User findByUsername(String username);
    public User login(String username, String password);
    public void save(User user);
}
