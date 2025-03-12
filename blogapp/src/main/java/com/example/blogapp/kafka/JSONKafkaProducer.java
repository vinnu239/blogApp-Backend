package com.example.blogapp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.blogapp.model.Blog;

@Service
public class JSONKafkaProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JSONKafkaProducer.class);
	
	private KafkaTemplate<String,Blog> kafkaTemplate;

	public JSONKafkaProducer(KafkaTemplate<String, Blog> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage (Blog blog) {
		
		Message<Blog> message = MessageBuilder.withPayload(blog).setHeader(KafkaHeaders.TOPIC,"JsonkafkaTopic").build();
		kafkaTemplate.send(message);
	}
	
	

}
