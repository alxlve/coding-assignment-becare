package com.alexislavie.coding.assignment.becare.repository;

import com.alexislavie.coding.assignment.becare.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

}
