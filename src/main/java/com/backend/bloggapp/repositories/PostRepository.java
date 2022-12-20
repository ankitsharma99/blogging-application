package com.backend.bloggapp.repositories;

import com.backend.bloggapp.entities.Category;
import com.backend.bloggapp.entities.Post;
import com.backend.bloggapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByPostTitleContaining(String title);
}
