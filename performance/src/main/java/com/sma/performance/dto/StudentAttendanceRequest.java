package com.sma.performance.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttendanceRequest {

	private Long teacher_id;
	
	private String std;

	private LocalDate date;

	private List<StudentAttendanceDetailsRequest> studentAttendanceRequest;
	

}
