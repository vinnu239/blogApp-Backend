package com.example.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	 List<Comment> findByPostId(Long postId);
}
