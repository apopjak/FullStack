package com.popjak.websiteproject.service;


import com.popjak.websiteproject.dao.*;
import com.popjak.websiteproject.entity.*;
import com.popjak.websiteproject.repository.*;
import com.popjak.websiteproject.security.*;
import jakarta.transaction.*;

import java.util.*;


@org.springframework.stereotype.Service
public class Service implements DAO {

    private final PasswordValidationCheck passwordValidationCheck;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    public Service(PasswordValidationCheck passwordValidationCheck, AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.passwordValidationCheck = passwordValidationCheck;
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {

        // DATA INPUT CLEANING
        user.setFirstName(user.getFirstName().toUpperCase());
        user.setLastName(user.getLastName().toUpperCase());
        user.setEmail(user.getEmail().toUpperCase());
        user.setPassword(passwordValidationCheck.passwordEncoder(user.getPassword()));
        user.setEnabled(1);
        user.setRole("EMPLOYEE");

        // IT SAVES USER AND AUTHORITY INTO DATABASE
        userRepository.save(user);
        Authority authority = new Authority(user.getId(),user.getEmail(),"EMPLOYEE");
        authorityRepository.save(authority);
    }

    @Override
    public boolean existsByEmail(String email) {

        // It checks it user exists in the database
        return userRepository.existsUserByEmail(email.toUpperCase());
    }

    @Override
    public void deleteById(int theId) {

        // It removes entity from DB both user and authority
        authorityRepository.deleteById(theId);
        userRepository.deleteById(theId);
    }

    @Override
    public User findUserById(Integer id) {

        // get user by ID
        Optional<User> result = userRepository.findById(id);
        User theUser = null;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("User does not exit. ID: " + id);
        }
        return theUser;
    }

    @Override
    public User findUserByEmail(String email) {
        // Gets user by email address
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, String firstName, String lastName, String role) {

        // Update User entity
        userRepository.updateUser(id,firstName,lastName,role);
    }

    @Override
    @Transactional
    public void updateAuthority(Integer id,  String role) {

        // Update Authority entity
        authorityRepository.updateAuthority(id,role);
    }


    @Override
    public void updatePassword(Integer id, String newPassword) {

        // password update
        userRepository.updatePassword(id, newPassword);
    }

}
