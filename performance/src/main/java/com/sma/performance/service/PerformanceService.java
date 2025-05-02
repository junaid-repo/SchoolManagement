package com.sma.performance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sma.performance.dto.StudentAttendanceById;
import com.sma.performance.dto.StudentAttendanceByIdResponse;
import com.sma.performance.dto.StudentAttendanceDetailsRequest;
import com.sma.performance.dto.StudentAttendanceRequest;
import com.sma.performance.entity.StaffAttendanceRequest;
import com.sma.performance.entity.StudentAttendance;
import com.sma.performance.entity.StudentAttendanceDetails;
import com.sma.performance.repos.StaffAttendanceRepository;
import com.sma.performance.repos.StudentAttendanceDetailsRepository;
import com.sma.performance.repos.StudentAttendanceRepository;

@Service
public class PerformanceService {

	@Autowired
	StudentAttendanceRepository stuAttenRepo;

	@Autowired
	StaffAttendanceRepository staAttenRepo;

	@Autowired
	StudentAttendanceDetailsRepository stuAttenDRepo;

	public String logStudentAttendence(StudentAttendanceRequest request) {

		var attendanceDetails = StudentAttendance.builder().date(request.getDate()).std(request.getStd())
				.teacher_id(request.getTeacher_id()).build();

		StudentAttendance savedAttendance = stuAttenRepo.save(attendanceDetails);

		if (savedAttendance != null) {
			request.getStudentAttendanceRequest().stream().forEach(obj -> {
				var attendanceDetails2 = StudentAttendanceDetails.builder().date(obj.getDate()).status(obj.isStatus())
						.studentId(obj.getStudentId()).remarks(obj.getRemarks())
						.studentAttendanceId(savedAttendance.getId()).build();

				stuAttenDRepo.save(attendanceDetails2);

			});
		}

		return "saved";
	}

	@Cacheable(value = "request")
	public StudentAttendanceRequest getStudentAttendence(LocalDate request) {
		StudentAttendanceRequest response = null;
		StudentAttendance stuAttn = stuAttenRepo.getByAttendanceDate(request);
		if (stuAttn != null) {
			List<StudentAttendanceDetails> stuAttnDetails = stuAttenDRepo.findbyStudentAttendanceId(stuAttn.getId());

			List<StudentAttendanceDetailsRequest> studentAttendanceRequest = new ArrayList<>();
			stuAttnDetails.stream().forEach(obj -> {
				studentAttendanceRequest.add(StudentAttendanceDetailsRequest.builder().date(obj.getDate())
						.studentId(obj.getStudentId()).status(obj.getStatus()).remarks(obj.getRemarks()).build());
			});
			response = StudentAttendanceRequest.builder().date(stuAttn.getDate()).teacher_id(stuAttn.getTeacher_id())
					.std(stuAttn.getStd()).studentAttendanceRequest(studentAttendanceRequest).build();

		}

		return response;
	}

	public StudentAttendanceByIdResponse getStudentAttendenceByStudentId(Integer studentId) {

		// Fetch student attendance details from the repository
		List<StudentAttendanceDetails> stuAttnDetails = stuAttenDRepo.findByStudentId(studentId);

		List<LocalDate> forPresent = stuAttnDetails.stream().filter(obj -> obj.getStatus()).map(obj -> obj.getDate())
				.collect(Collectors.toList());
		List<LocalDate> forAbsent = stuAttnDetails.stream().filter(obj -> obj.getStatus() == false)
				.map(obj -> obj.getDate()).collect(Collectors.toList());

		List<StudentAttendanceById> studentAttendanceById1 = List.of(
				StudentAttendanceById.builder().status(true).date(forPresent).build(),
				StudentAttendanceById.builder().status(false).date(forAbsent).build());

		return StudentAttendanceByIdResponse.builder().studentAttendanceById(studentAttendanceById1).build();
	}

	public String logStaffAttendence(StaffAttendanceRequest request) {

		List<StaffAttendanceRequest> attendList = staAttenRepo.getByStaffIdAndDate(request.getTeacherId(),
				request.getDate());

		if (attendList.size() > 0) {
			return "attendence already added";
		} else
			staAttenRepo.save(request);

		return "attendence added successfully";
	}
	
	public StudentAttendanceByIdResponse getStaffAttendenceByStaffId(Integer staffId) {

		// Fetch student attendance details from the repository
		List<StaffAttendanceRequest> staAttnDetails = staAttenRepo.findByStaffId(staffId);

		List<LocalDate> forPresent = staAttnDetails.stream().filter(obj -> obj.getStatus()).map(obj -> obj.getDate())
				.collect(Collectors.toList());
		List<LocalDate> forAbsent = staAttnDetails.stream().filter(obj -> obj.getStatus() == false)
				.map(obj -> obj.getDate()).collect(Collectors.toList());

		List<StudentAttendanceById> studentAttendanceById1 = List.of(
				StudentAttendanceById.builder().status(true).date(forPresent).build(),
				StudentAttendanceById.builder().status(false).date(forAbsent).build());

		return StudentAttendanceByIdResponse.builder().studentAttendanceById(studentAttendanceById1).build();
	}

}
