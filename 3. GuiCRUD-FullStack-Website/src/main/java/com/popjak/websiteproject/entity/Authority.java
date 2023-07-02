package com.popjak.websiteproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authorities")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "authority")
    private String authorityRole;
}
