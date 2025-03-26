package com.sma.performance.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sma.performance.entity.StudentAttendance;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {

	@Query(value="select *  from student_attendance sa where sa.date=?1 order by created_at desc limit 1 ", nativeQuery=true)
	StudentAttendance getByAttendanceDate(LocalDate request);

}
