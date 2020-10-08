package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_info")
@Data
@RequiredArgsConstructor
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String surname;
    private String email;
    private String login;
    @Column(name = "password_hash")
    private String passwordHash;

    @CreationTimestamp
    @GeneratedValue
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;
}
