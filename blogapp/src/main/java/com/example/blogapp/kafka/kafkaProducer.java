package com.example.blogapp.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
	
	 private static final String TOPIC = "kafkaTopic";
	private KafkaTemplate<String,String>kafkaTemplate;
	
	// we will inject constructor injection to access the kafka template

	public kafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	public void sendMessage(String message) {
		
		LOGGER.info(String.format("Message sent",message ));
		kafkaTemplate.send(TOPIC, message);
	}
	
	
	
}
