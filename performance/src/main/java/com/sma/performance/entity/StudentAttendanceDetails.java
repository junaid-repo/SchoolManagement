package com.sma.performance.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_attendance_details")
public class StudentAttendanceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "status", nullable = false)
    private Boolean status; // Example: "Present", "Absent", "Late"

    @Column(name = "remarks")
    private String remarks;

    // Additional fields for audit purposes (optional)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
   
    @Column(name="studentAttendance_id", nullable = false)
    private Long studentAttendanceId;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}