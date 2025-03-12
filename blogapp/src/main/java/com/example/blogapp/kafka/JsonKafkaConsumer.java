package com.example.blogapp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.blogapp.model.Blog;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonKafkaConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
	@Autowired
    private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "JsonkafkaTopic",groupId = "myGroup")
	public void consume(Blog blog) {
		 try {
	            String json = objectMapper.writeValueAsString(blog);
	            LOGGER.info(String.format("Json message received -> %s", json));
	        } catch (Exception e) {
	            LOGGER.error("Error converting Blog object to JSON", e);
	        }
		
	}

}
