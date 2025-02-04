package com.example.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blogapp.model.Blog;

@Repository
public interface PostRepository extends JpaRepository<Blog, Long> {
}
