package com.sma.students.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sma.students.entity.StudentDetails;
import com.sma.students.service.StudentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sm/students")
@Slf4j
public class ControllerStudent {
	
	@Autowired
	StudentService serv;
	
	@GetMapping("/hello")
	ResponseEntity<String> helloWorld(){
		return ResponseEntity.status(HttpStatus.OK).body("Hello World");
	}
	
	@PostMapping("/registerStudent")
	ResponseEntity<Map> registerStudent(@Valid @RequestBody StudentDetails request){
		
		String response=serv.saveStudentDetails(request);
		Map<String, String> responseMap=new HashMap<>();
		responseMap.put("admissionNumber", response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
	}
	@GetMapping("/getStudentDetails")
	ResponseEntity<StudentDetails> getStudentDetails(@RequestParam String admitNumber){
		
		StudentDetails response=serv.getStudentDetails(admitNumber);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/updateStudentDetails")
	ResponseEntity<StudentDetails> updateStudentDetails(@RequestBody StudentDetails studentDetails){
		
		StudentDetails response=null;
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	/*
	 * @GetMapping("/getAttendence") ResponseEntity<List<Attendence>>
	 * getAttendence(@RequestParam String admitNumber){
	 * 
	 * List<Attendence> response=null;
	 * 
	 * return ResponseEntity.status(HttpStatus.CREATED).body(response); }
	 * 
	 * @GetMapping("/getExamResult") ResponseEntity<List<ExamResult>>
	 * getExamResult(@RequestParam String admitNumber){
	 * 
	 * List<ExamResult> response=null;
	 * 
	 * return ResponseEntity.status(HttpStatus.CREATED).body(response); }
	 */

}
