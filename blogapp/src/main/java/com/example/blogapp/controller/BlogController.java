package com.example.blogapp.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.blogapp.service.BlogService;
import com.example.blogapp.kafka.kafkaProducer;
import com.example.blogapp.model.Blog;
import com.example.blogapp.model.Comment;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;
    
    
    
    private kafkaProducer kafkaProducer;
    
    

    public BlogController(kafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
    
    //http:localhost:8080/blogs/kafka/publish?message=hello world
    
    @GetMapping("/kafka/publish")
    
    public ResponseEntity<String> publish(@RequestParam("message") String message){
    	
    	kafkaProducer.sendMessage(message);
    	return ResponseEntity.ok("Message sent to the topic");
    }
    

	@GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // we can use @RequestParam instead of @pathvariable as well 
    //http://localhost:8080/users?id=123
//    @GetMapping("/users")
//    public String getUserById(@RequestParam String id) {
//        return "User ID: " + id;
//    }
    
    
    //http://localhost:8080/users/123
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