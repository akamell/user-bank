package com.colpatria.akp.demo.repositories;

import java.util.List;
import javax.persistence.EntityManager;

import com.colpatria.akp.demo.dtos.UserResponse;
import com.colpatria.akp.demo.entities.User;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User", User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }

    @Override
    public UserResponse findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        var user = currentSession.get(User.class, id);
        UserResponse userDto = new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getName(),
            user.getLastname(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
        return userDto;
    }

    @Override
    public User findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User where username=:username", User.class);
        query.setParameter("username", username);
        var result = query.getResultList();
        if(result.isEmpty()) return null;

        return result.get(0);
    }

    @Override
    public User login(String username, String password) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User where username=:username and password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        var result = query.getResultList();
        if(result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
    }
}
