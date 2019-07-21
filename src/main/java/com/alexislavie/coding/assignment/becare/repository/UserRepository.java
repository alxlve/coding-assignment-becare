package com.alexislavie.coding.assignment.becare.repository;

import com.alexislavie.coding.assignment.becare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    long countByEmail(String email);

    Optional<User> findByEmail(String email);
}
