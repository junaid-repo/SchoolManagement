package com.sma.teacher.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sma.teacher.entity.StaffDetails;
import com.sma.teacher.service.StaffService;

@RestController
@RequestMapping("/sm/teacher")
public class ControllerTeacher {
	
	@Autowired
	StaffService serv;
	
	@PostMapping("/staff/register")
	ResponseEntity<Integer> registerStaff(@RequestBody StaffDetails request){
		
		Integer status=serv.registerStaff(request);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(status);
		
		
	}
	
	@PostMapping("/log/attendence")
	ResponseEntity<Boolean> logAttendence(@RequestBody Map<String, String> request){
		
		Boolean status=serv.logAttendence(request);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(status);
		
		
	}

}
