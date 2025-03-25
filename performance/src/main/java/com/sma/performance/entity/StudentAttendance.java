package com.sma.performance.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "student_attendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "teacher_id", nullable = false)
	private Long teacher_id;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	// Additional fields for audit purposes (optional)
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}