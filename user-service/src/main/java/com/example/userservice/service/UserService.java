package com.example.userservice.service;

import com.example.userservice.entity.User;

public interface UserService {
    boolean validateUser(Long userId);
    User createUser(User user);
}
