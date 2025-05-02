package com.sma.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sma.staff.entity.StaffDetails;
import com.sma.staff.service.StaffService;

@RestController
@RequestMapping("/sm/staff")
public class ControllerStaff {

	@Autowired
	StaffService serv;

	@PostMapping("/register")
	ResponseEntity<Integer> registerStaff(@RequestBody StaffDetails request) {

		Integer status = serv.registerStaff(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(status);

	}

	@GetMapping("/ids")
	ResponseEntity<List<Integer>> getStaffids() {

		List<Integer> ids = serv.getStaffIds();

		return ResponseEntity.status(HttpStatus.CREATED).body(ids);

	}
	@GetMapping("/details/get/{id}")
	ResponseEntity<StaffDetails> getStaffids(@PathVariable String id) {

		StaffDetails details = serv.getStaffDetails(id);

		return ResponseEntity.status(HttpStatus.CREATED).body(details);

	}

}
