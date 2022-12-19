package com.backend.bloggapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "post_title", length = 100, nullable = false)
    private String postTitle;
    @Column(name = "content", length = 10000)
    private String content;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "added_date")
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
