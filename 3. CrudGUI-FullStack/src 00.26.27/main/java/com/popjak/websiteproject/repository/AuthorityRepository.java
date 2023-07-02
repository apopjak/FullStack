package com.popjak.websiteproject.repository;


import com.popjak.websiteproject.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    @Modifying
    @Query("UPDATE Authority a SET a.id = :id, a.authorityRole = :role WHERE a.id = :id")
    void updateAuthority(@Param("id") Integer Id, @Param("role") String role);
}