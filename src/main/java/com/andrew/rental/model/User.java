package com.andrew.rental.model;

import com.andrew.rental.AddUserRequest;
import com.andrew.rental.UsersShort;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.protobuf.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
@DynamicUpdate
@NoArgsConstructor
@Builder
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

    public UsersShort toUsersShort() {
        Timestamp convertedCreatedAt = Timestamp.newBuilder().
                setSeconds(createdAt.getSecond()).
                setNanos(createdAt.getNano()).
                build();

        return UsersShort.newBuilder().
                setId(id.toString()).
                setSurname(surname).
                setName(name).
                setCreatedAt(convertedCreatedAt)
                .setEmail(email)
                .setLogin(login)
                .setPasswordHash(passwordHash)
                .setRole(com.andrew.rental.Role.valueOf(role.toString()))
                .build();
    }

    public static User fromAddRequest(AddUserRequest userRequest) {
        return new UserBuilder().
                name(userRequest.getName()).
                surname(userRequest.getSurname()).
                email(userRequest.getEmail()).
                login(userRequest.getLogin()).
                passwordHash(userRequest.getPasswordHash()).
                role(Role.valueOf(userRequest.getRole().toString())).
                build();
    }
}
