package com.sma.teacher.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.teacher.entity.StaffDetails;
import com.sma.teacher.repos.StaffSaveRepository;

@Component
public class StaffService {
	
	@Autowired
	private StaffSaveRepository ssRepo;

	public Boolean logAttendence(Map<String, String> request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer registerStaff(StaffDetails request) {
		
		return ssRepo.save(request).getId();
	}

}
