package com.sma.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sma.notification.dto.EmailRequest;
import com.sma.notification.service.NotificationService;

@RestController
@RequestMapping("/sm/notify")
public class ControllerNotification {

	@Autowired
	NotificationService serv;

	@PostMapping("/send/email")
	ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {

		String response = serv.sendEmail(request);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
	@PostMapping("email/saveTemplate")
	ResponseEntity<String> saveTemplate(@RequestBody EmailRequest request) {

		String response = serv.sendEmail(request);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
}
