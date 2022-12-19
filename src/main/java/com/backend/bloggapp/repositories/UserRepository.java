package com.backend.bloggapp.repositories;

import com.backend.bloggapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
