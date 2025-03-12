package com.example.blogapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapp.kafka.JSONKafkaProducer;
import com.example.blogapp.model.Blog;

@RestController
@RequestMapping("/api/v1/kafka")
public class JSONKafkaController {
	
	private JSONKafkaProducer kafkaProducer;

	public JSONKafkaController(JSONKafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> publish (@RequestBody Blog blog){
		
		kafkaProducer.sendMessage(blog);
		
		return ResponseEntity.ok("JSON message sent to Kafka Topic");
		
	}
	
	

}
