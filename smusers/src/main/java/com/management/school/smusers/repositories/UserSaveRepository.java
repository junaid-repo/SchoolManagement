package com.management.school.smusers.repositories;

import com.management.school.smusers.entities.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSaveRepository extends JpaRepository<UserCredential, Integer> {
}
