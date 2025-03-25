package com.sma.performance.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttendanceDetailsRequest {

	private Long studentId;

	private LocalDate date;

	private boolean status;

	private String remarks;
}
