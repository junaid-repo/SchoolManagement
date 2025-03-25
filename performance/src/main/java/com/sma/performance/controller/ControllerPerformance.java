package com.sma.performance.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sma.performance.dto.StudentAttendanceRequest;
import com.sma.performance.entity.StudentAttendance;
import com.sma.performance.service.PerformanceService;

@RestController
@RequestMapping("/sm/performance")
public class ControllerPerformance {
	
	@Autowired
	PerformanceService serv;
	
	@PostMapping("/log/attendence/student")
	ResponseEntity<String> logStudentAttendence(@RequestBody StudentAttendanceRequest request){
		
		String response=serv.logStudentAttendence(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
		
	}
	@GetMapping("/get/attendence/student/byDate")
	ResponseEntity<StudentAttendanceRequest> getStudentAttendence(@RequestParam LocalDate request){
		
		StudentAttendanceRequest response=serv.getStudentAttendence(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
		
	}

}
