package com.sma.performance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sma.performance.dto.StudentAttendanceDetailsRequest;
import com.sma.performance.dto.StudentAttendanceRequest;
import com.sma.performance.entity.StudentAttendance;
import com.sma.performance.entity.StudentAttendanceDetails;
import com.sma.performance.repos.StudentAttendanceDetailsRepository;
import com.sma.performance.repos.StudentAttendanceRepository;

@Service
public class PerformanceService {

	@Autowired
	StudentAttendanceRepository stuAttenRepo;

	@Autowired
	StudentAttendanceDetailsRepository stuAttenDRepo;

	public String logStudentAttendence(StudentAttendanceRequest request) {

		var attendanceDetails = StudentAttendance.builder().date(request.getDate()).teacher_id(request.getTeacher_id())
				.build();

		StudentAttendance savedAttendance = stuAttenRepo.save(attendanceDetails);

		if (savedAttendance != null) {
			request.getStudentAttendanceRequest().stream().forEach(obj -> {
				var attendanceDetails2 = StudentAttendanceDetails.builder().date(obj.getDate()).status(obj.isStatus())
						 .studentId(obj.getStudentId())
						.remarks(obj.getRemarks()).studentAttendanceId(savedAttendance.getId()).build();

				stuAttenDRepo.save(attendanceDetails2);

			});
		}

		return "saved";
	}

	public StudentAttendanceRequest getStudentAttendence(LocalDate request) {
		StudentAttendanceRequest response = null;
		StudentAttendance stuAttn = stuAttenRepo.getByAttendanceDate(request);
		if (stuAttn != null) {
			List<StudentAttendanceDetails> stuAttnDetails = stuAttenDRepo.findbyStudentAttendanceId(stuAttn.getId());

			response = StudentAttendanceRequest.builder().date(stuAttn.getDate()).teacher_id(stuAttn.getTeacher_id())
					.build();

			List<StudentAttendanceDetailsRequest> studentAttendanceRequest = new ArrayList<>();
			stuAttnDetails.stream().forEach(obj -> {
				studentAttendanceRequest.add(StudentAttendanceDetailsRequest.builder().date(obj.getDate())
						.studentId(obj.getStudentId()).status(obj.getStatus()).remarks(obj.getRemarks()).build());
			});
			response = StudentAttendanceRequest.builder().date(stuAttn.getDate()).teacher_id(stuAttn.getTeacher_id())
					.studentAttendanceRequest(studentAttendanceRequest).build();

		}

		return response;
	}

}
