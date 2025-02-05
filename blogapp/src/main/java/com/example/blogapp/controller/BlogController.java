package com.example.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.blogapp.service.BlogService;
import com.example.blogapp.model.Blog;
import com.example.blogapp.model.Comment;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    
    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @PostMapping("/create")
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }

    @PostMapping("/createcomment")
    public Comment createComment(@RequestBody Comment comment) {
    	System.out.println(comment);
        if (comment.getPostId() == null) {
            throw new IllegalArgumentException("ID must be provided");
        }
        return blogService.createComment(comment);
    }
    
    
    @GetMapping("getAllComments/{postId}")
    public List<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
        return blogService.getCommentsByPostId(postId);
    }
    
    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        blog.setId(id);
        return blogService.saveBlog(blog);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
    }
}