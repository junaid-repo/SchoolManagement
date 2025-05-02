package com.sma.scheduler.service.api;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sma.scheduler.dto.EmailRequest;
import com.sma.scheduler.dto.StudentAttendanceByIdResponse;

@Service
public class ApiClientService {
	private final WebClient webClient;

	@Value("${service.token.username}")
	private String username;
	@Value("${service.token.password}")
	private String pass;

	public ApiClientService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:8090/").build();
	}

	public List getAllStaffId() {

		String token = generateToken();

		List response = webClient.get().uri("sm/staff/ids").header("Authorization", "Bearer " + token) // Adding Bearer
																										// Token
				.retrieve().bodyToMono(List.class).block();

		System.out.println(response);
		return response;
	}

	public String generateToken() {

		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("username", username);
		requestMap.put("password", pass);

		String token;

		token = webClient.post().uri("auth/token").bodyValue(requestMap).retrieve().bodyToMono(String.class).block();
		System.out.println(token);
		return token;
	}

	public void getAndSaveAttendence(Integer id) {
		StudentAttendanceByIdResponse response = webClient.get()
				.uri("sm/performance/get/attendence/staff/byStaffId?id=" + id)
				.header("Authorization", "Bearer " + generateToken()) // Adding Bearer Token
				.retrieve().bodyToMono(StudentAttendanceByIdResponse.class).block();
		Map<String, Object> logRequest = new HashMap<>();
		response.getStudentAttendanceById().stream().filter(obj -> obj.isStatus()).forEach(i -> {
			i.getDate().stream().forEach(date -> {
				DayOfWeek dayOfWeek = date.getDayOfWeek();
				System.out.println("The day of the week is " + dayOfWeek);

				if (LocalDate.now().equals(date) == false && !(LocalDate.now().getDayOfWeek().equals(DayOfWeek.SATURDAY)
						&& LocalDate.now().getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
					if (!(dayOfWeek.equals(DayOfWeek.SATURDAY) && dayOfWeek.equals(DayOfWeek.SUNDAY))) {
						logRequest.put("teacherId", id);
						logRequest.put("status", false);
						logRequest.put("inTime", null);
						logRequest.put("outTime", null);
						logRequest.put("date", LocalDate.now().plusDays(4));

					}
				}
				if (logRequest.size() != 0) {
					saveSAttendence(logRequest);
					sendEmailReminder(logRequest);
				}

			});

		});

		System.out.println(response);

	}

	private void sendEmailReminder(Map<String, Object> logRequest) {

		Map<String, String> detailsMap = getStaffDetails((Integer) logRequest.get("teacherId"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String formattedDate = ((LocalDate) logRequest.get("date")).format(formatter);
		detailsMap.put("date", formattedDate);

		System.out.println("The staffDetails are " + detailsMap);

		sendEmail(detailsMap, "ATTNREM");

	}

	private Map<String, String> getStaffDetails(Integer id) {

		return webClient.get().uri("sm/staff/details/get/" + String.valueOf(id))
				.header("Authorization", "Bearer " + generateToken()) // Adding Bearer Token
				.retrieve().bodyToMono(Map.class).block();

	}

	private void saveSAttendence(Map<String, Object> logRequest) {

		String response = webClient.post().uri("sm/performance/log/attendence/staff").bodyValue(logRequest)
				.header("Authorization", "Bearer " + generateToken()).retrieve().bodyToMono(String.class).block();

		System.out.println("Attendance save response " + response);

	}

	public Integer sendEmail(Map staffDetails, String emailTemplate) {

		if (emailTemplate.equals("ATTNREM")) {
			var emailRequest = EmailRequest.builder().toEmailId((String) staffDetails.get("email"))
					.fromEmailId("tahanasim3001@gmail.com").receiptName((String) staffDetails.get("name"))
					.senderName("THE GOAT SCHOOL").subject("Missing Attendence Record ")
					.content("Your attendece is missing for date " + (String) staffDetails.get("date")
							+ " Please regularize your attendence or apply appropriate leave")
					.build();
			String url = "http://localhost:8090/sm/notify/send/email";
			System.out.println("The formed email request -->" + emailRequest.toString());
			String response = webClient.post().uri(url).header("Content-Type", "application/json")
					.bodyValue(emailRequest).retrieve().bodyToMono(String.class).block();
			return Integer.parseInt(response);
		}

		return null;

	}
}
