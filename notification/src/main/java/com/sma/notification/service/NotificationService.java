package com.sma.notification.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.sma.notification.dto.EmailRequest;

@Component
public class NotificationService {



	@Autowired
	private KafkaTemplate<String, EmailRequest> kafkaTemplate;

	@Autowired
	private NewTopic newTopic;

	public String sendEmail(EmailRequest request) {
		sendEmailWithKafka(request);
		
		return "success";
	}

	private Boolean sendEmailWithKafka(EmailRequest request) {

		Message<EmailRequest> message = MessageBuilder.withPayload(request)
				.setHeader(KafkaHeaders.TOPIC, newTopic.name()).build();

		kafkaTemplate.send(message);

		return true;

	}

}
