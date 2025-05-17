package com.sma.scheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sma.scheduler.service.api.ApiClientService;

@Component
public class ScheduledService {

	@Autowired
	ApiClientService apiClient;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void scheduledTask() {
		System.out.println("Executing task at: " + System.currentTimeMillis());
		List<Integer> staffIds = apiClient.getAllStaffId();
		
		staffIds.stream().forEach(id->{
			if(id==775)
				apiClient.getAndSaveAttendence(id);
		});
		

	}
}
