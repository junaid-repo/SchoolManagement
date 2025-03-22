package com.sma.students.external.api;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.sma.students.entity.StudentDetails;

@Component
public class ExternalApi {

	WebClient webClient;

	public ExternalApi() {
		this.webClient = WebClient.create();
	}

	@Async
	public CompletableFuture<Integer> sendEmail(StudentDetails studentDetails, String emailTemplate) {

		if (emailTemplate.equals("STUREG")) {
			var emailRequest = EmailRequest.builder().toEmailId(studentDetails.getEmail())
					.fromEmailId("tahanasim3001@gmail.com")
					.receiptName(studentDetails.getFirstName().concat(" ").concat(studentDetails.getLastName()))
					.senderName("THE GOAT SCHOOL").subject("Student Registration ")
					.content("You are registered to the GOAT School and your admission Number "
							+ studentDetails.getAdmissionNumber())
					.build();
			String url = "http://localhost:8090/sm/notify/send/email";
			System.out.println("The formed email request -->" + emailRequest.toString());
			String response = webClient.post().uri(url).header("Content-Type", "application/json")
					.bodyValue(emailRequest).retrieve().bodyToMono(String.class).block();
			return CompletableFuture.completedFuture(Integer.parseInt(response));
		}

		return null;

	}

}
