package com.example.securityproject.service;

import com.example.securityproject.domain.Role;
import com.example.securityproject.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    User getUser(String username);
    List<User> getUsers();
}
