package com.example.blogapp.controller;

import com.example.blogapp.model.Blog;
import com.example.blogapp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<String> createBlog(@RequestBody Blog blog) throws ExecutionException, InterruptedException {
        String id = blogService.saveBlog(blog);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() throws ExecutionException, InterruptedException {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable String id) throws ExecutionException, InterruptedException {
        Blog blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable String id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}