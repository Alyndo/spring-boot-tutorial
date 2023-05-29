package com.alwyn.techie.repository;

import com.alwyn.techie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
