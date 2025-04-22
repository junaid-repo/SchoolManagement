package com.sma.notification.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.sma.notification.contants.AppConstants;

@Configuration
public class NotificationsConfig {
	
	@Value("${spring.kafka.topic.name}")
	private String topic;

	@Bean
	public NewTopic topic() {

		return TopicBuilder.name(topic)
//                .partitions()
//                .replicas()
				.build();

	}
}
