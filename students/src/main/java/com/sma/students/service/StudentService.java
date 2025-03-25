package com.sma.students.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.sma.students.entity.StudentDetails;
import com.sma.students.external.api.ExternalApi;
import com.sma.students.repository.StudentSaveRepository;

@Component
public class StudentService {

	@Autowired
	StudentSaveRepository stuSaveRepo;

	@Autowired
	ExternalApi ea;

	public String saveStudentDetails(StudentDetails request) {

		StudentDetails stu = stuSaveRepo.save(request);
		String admitNumber = "SRH000" + String.valueOf(stu.getId()) + "/"
				+ String.valueOf(LocalDateTime.now().getYear());

		stu.setAdmissionNumber(admitNumber);
		stuSaveRepo.save(stu);

		CompletableFuture.supplyAsync(() -> {
			System.out.println("Inside StudentService sending email for admitNumber--> " + admitNumber);

			return ea.sendEmail(getStudentDetails(admitNumber), "STUREG");

		});

		return admitNumber;
	}

	@Cacheable("studentCache")
	public StudentDetails getStudentDetails(String admitNumber) {

		StudentDetails stu = stuSaveRepo.getByAdmissionNumber(admitNumber);

		return stu;
	}

}
