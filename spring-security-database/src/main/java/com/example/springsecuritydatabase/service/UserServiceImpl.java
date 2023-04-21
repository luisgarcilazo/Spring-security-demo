package com.example.springsecuritydatabase.service;

import com.example.springsecuritydatabase.dao.UserDao;
import com.example.springsecuritydatabase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    @Override
    public User getUserById(int userID) {
        Optional<User> c = this.userDao.findById(userID);
        User user = null;
        if(c.isPresent()){
            user = c.get();
            return user;
        } else {
            throw new RuntimeException("User not found for id :: " + userID);
        }
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userDao.save(user);
    }

    @Override
    public String deleteUserById(int userID) {
        this.userDao.deleteById(userID);
        return "Deleted successfully!";
    }

}
