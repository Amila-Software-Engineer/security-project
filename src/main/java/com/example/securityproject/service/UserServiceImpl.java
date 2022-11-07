package com.example.securityproject.service;

import com.example.securityproject.domain.Role;
import com.example.securityproject.domain.User;
import com.example.securityproject.repo.RoleRepository;
import com.example.securityproject.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements  UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =userRepository.findByUsername(username);
       if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
       }else{
           log.info("User found in the database: {}" , username);
       }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       user.getRoles().forEach(role->{
           authorities.add(new SimpleGrantedAuthority(role.getName()));
       });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public User saveUser(User user) {
        log.info("Saving new User {} to the database",user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role {} to the database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding role {} to user {}",rolename, username);
        User user= userRepository.findByUsername(username);
        Role role= roleRepository.findByname(rolename);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching All users");
        return userRepository.findAll();
    }


}
