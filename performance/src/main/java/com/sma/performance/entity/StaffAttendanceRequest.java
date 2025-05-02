package com.sma.performance.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "staff_attendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffAttendanceRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer teacherId;
	private Boolean status;
	private LocalDateTime inTime;
	private LocalDateTime outTime;
	private LocalDate date;
}
