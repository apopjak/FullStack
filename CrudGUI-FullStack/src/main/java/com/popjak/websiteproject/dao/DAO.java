package com.popjak.websiteproject.dao;


import com.popjak.websiteproject.entity.*;

import java.util.*;

public interface DAO {

    ///////////////---- CREATE ----///////////////
    void save(User user);


    ///////////////---- READ ----///////////////
    List<User> findAll();
    User findUserById(Integer id);
    User findUserByEmail(String email);



    ///////////////---- UPDATE ----//////////////
    void updateUser(Integer id, String firstName, String lastName, String role);

    void updateAuthority(Integer id, String role);

    void updatePassword(Integer id, String newPassword);



    ///////////////---- DELETE ----///////////////
    void deleteById(int theId);


    ///////////////---- BOOLEANS ----//////////////
    boolean existsByEmail(String email);
}
