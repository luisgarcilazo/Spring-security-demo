package com.example.springsecuritydatabase.service;

import com.example.springsecuritydatabase.dao.UserDao;
import com.example.springsecuritydatabase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsInfoService implements UserDetailsService {

    //I was following some of what was shown in this video: https://www.youtube.com/watch?v=R76S0tfv36w

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> c = this.userDao.findByName(username);
        User user = null;
        if(c.isPresent()){
            user = c.get();
            UserDetails uDetails = new MyUserDetails(user);
            return uDetails;
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
