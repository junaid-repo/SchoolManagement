package com.sma.performance.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sma.performance.entity.StudentAttendanceDetails;

public interface StudentAttendanceDetailsRepository extends JpaRepository<StudentAttendanceDetails, Long> {

	@Query(value="select * from student_attendance_details std where std.student_attendance_id=?1", nativeQuery=true)
	List<StudentAttendanceDetails> findbyStudentAttendanceId(Long id);

}
