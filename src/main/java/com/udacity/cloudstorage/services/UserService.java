package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.UserMapper;
import com.udacity.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Shubham Sharma
 * @date 6/4/20
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("user not found with username = %s", username));
        }
        return user;
    }

    public User register(User user) throws Exception {
        String encodedPW = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPW);
        user.setEnabled(true);
        user.setRole("USER");
        try {
            userMapper.insertUser(user);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    public Boolean isUserRegistered(String  username){
        return Optional.ofNullable(userMapper.findByUsername(username)).isPresent();
    }
}
