package com.sma.staff.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.staff.entity.StaffDetails;
import com.sma.staff.repos.StaffSaveRepository;

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

	public List<Integer> getStaffIds() {
		
		return ssRepo.findAllStaffIds();
	}

	public StaffDetails getStaffDetails(String id) {
		// TODO Auto-generated method stub
		return ssRepo.findById(Integer.parseInt(id)).get();
	}

}
