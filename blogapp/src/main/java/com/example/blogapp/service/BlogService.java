package com.example.blogapp.service;
import com.example.blogapp.model.Blog;
import com.example.blogapp.model.Comment;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
    
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }


    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }


    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

	public Comment createComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.save(comment);
	}
}