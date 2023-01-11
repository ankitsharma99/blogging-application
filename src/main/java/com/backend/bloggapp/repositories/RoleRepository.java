package com.backend.bloggapp.repositories;

import com.backend.bloggapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
