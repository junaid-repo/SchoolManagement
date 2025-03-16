package com.sma.students.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sma.students.entity.StudentDetails;

@RestController
@RequestMapping("/sm/students")
public class ControllerStudent {
	
	@GetMapping("/hello")
	ResponseEntity<String> helloWorld(){
		return ResponseEntity.status(HttpStatus.OK).body("Hello World");
	}
	
	@PostMapping("/registerStudent")
	ResponseEntity<String> registerStudent(@RequestBody StudentDetails request){
		
		String response=null;
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@GetMapping("/getStudentDetails")
	ResponseEntity<StudentDetails> getStudentDetails(@RequestParam String admitNumber){
		
		StudentDetails response=null;
		
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
