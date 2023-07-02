package com.popjak.websiteproject.repository;


import com.popjak.websiteproject.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByEmail(String email);

    boolean existsUserByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName,u.role = :role WHERE u.id = :userId")
    void updateUser(@Param("userId") Integer userId, @Param("firstName") String firstName, @Param("lastName") String lastName,  @Param("role") String role);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.id = :userId")
    void updatePassword(@Param("userId") Integer userId, @Param("newPassword") String newPassword);
}