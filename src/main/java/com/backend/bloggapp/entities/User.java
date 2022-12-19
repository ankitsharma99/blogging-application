package com.backend.bloggapp.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Column(name = "user_name", nullable = false)
	private String name;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "about", nullable = false)
	private String about;
	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> postList = new ArrayList<>();
}
