package com.colpatria.akp.demo.services;

import java.security.MessageDigest;
import java.util.List;

import com.colpatria.akp.demo.dtos.UserResponse;
import com.colpatria.akp.demo.entities.User;
import com.colpatria.akp.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository _userRepo;

    @Override
    public List<User> findAll() {
        return _userRepo.findAll();
    }

    @Override
    public UserResponse findById(int id) {
        return _userRepo.findById(id);
    }

    @Override
    public User login(String username, String password){
        String hashPassword = sha256(password);
        return _userRepo.login(username, hashPassword);
    }
    
    @Override
    public User findByUsername(String username) {
        return _userRepo.findByUsername(username);
    }

    @Override
    public void save(User user) {
        String hashPassword = sha256(user.getPassword());
        user.setPassword(hashPassword);
        _userRepo.save(user);
    }

    public String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) 
                  hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }
}
