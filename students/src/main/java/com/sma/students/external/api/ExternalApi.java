package com.sma.students.external.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.sma.students.entity.StudentDetails;

@Component
public class ExternalApi {

	WebClient webClient;

	@Value("${security.authorization.username}")
	String username;
	@Value("${security.authorization.password}")
	String password;

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

	public Map<String, Object> getAttendanceDetails(String admitNumber) {

		Map<String, Object> apiResponse = new HashMap<>();

		String uri = "http://localhost:8090/sm/performance/get/attendence/student/byStudentId?request=" + admitNumber;

		String token = getAuthorizationToken();
		System.out.println("After calling token service");
		System.out.println(token);
		apiResponse = webClient.get().uri(uri).header("Authorization", "Bearer " + token).retrieve()
				.bodyToMono(Map.class).block();

		return apiResponse;
	}

	private String getAuthorizationToken() {

		String uri = "http://localhost:8090/auth/token";
		Map<String, String> requestMap = new HashMap<>();

		requestMap.put("username", username);
		requestMap.put("password", password);

		return webClient.post().uri(uri).header("Content-Type", "application/json").bodyValue(requestMap).retrieve()
				.bodyToMono(String.class).block();

	}

}
