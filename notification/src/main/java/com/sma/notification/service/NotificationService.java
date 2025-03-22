package com.sma.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.notification.dto.EmailRequest;
import com.sma.notification.external.api.SendEmailApi;

@Component
public class NotificationService {

	@Autowired
	SendEmailApi sma;

	public String sendEmail(EmailRequest request) {

		return sma.sendEmail(request);
	}

}
