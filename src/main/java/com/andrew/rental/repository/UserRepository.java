package com.andrew.rental.repository;

import com.andrew.rental.model.Role;
import com.andrew.rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByRole(Role role);
}

