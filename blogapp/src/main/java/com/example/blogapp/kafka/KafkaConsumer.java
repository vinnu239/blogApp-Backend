package com.example.blogapp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	public static final Logger Logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	// so after producer send message to the kafka topic by using below Listener the conumer will subscribe to the topic
	@KafkaListener(topics = "kafkaTopic",groupId = "myGroup")
	public void consume(String message) {
		
		// apply any logic you want
	Logger.info(String.format("messsage received -> %s",message));
	System.out.print(message);
	}

}
