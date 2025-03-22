package com.sma.notification.external.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.sma.notification.dto.EmailRequest;

@Component
public class SendEmailApi {
	
	private final WebClient webClient;

    public SendEmailApi() {
        this.webClient = WebClient.create();
    }
    public String sendEmail(EmailRequest emailRequest) {
        String url = "http://localhost:6060/email/send"; // Replace with your actual API endpoint

        String response = webClient.post()
                .uri(url)
                .header("Content-Type", "application/json") // Set headers if required
                .bodyValue(emailRequest) // Attach the request body
                .retrieve()
                .bodyToMono(String.class) // Map the response to a String (or another object)
                .block(); // For synchronous call; replace with subscribe() for async

        return response;
    }
}
