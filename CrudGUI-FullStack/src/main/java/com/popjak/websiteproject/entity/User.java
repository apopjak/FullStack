package com.popjak.websiteproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "incrementing", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "incrementing", sequenceName = "incrementing", allocationSize = 1, initialValue = 100)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "role")
    private String role;
}






