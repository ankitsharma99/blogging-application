package com.backend.bloggapp.repositories;

import com.backend.bloggapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
