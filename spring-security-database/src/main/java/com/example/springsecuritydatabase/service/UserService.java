package com.example.springsecuritydatabase.service;

import com.example.springsecuritydatabase.entity.User;

import java.util.List;
public interface UserService {

    List<User> getAllUsers();

    User getUserById(int userID);

    User addUser(User user);

    User updateUser(User user);

    String deleteUserById(int userID);

}
