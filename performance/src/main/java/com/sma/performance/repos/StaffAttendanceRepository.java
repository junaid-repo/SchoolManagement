package com.sma.performance.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sma.performance.entity.StaffAttendanceRequest;
import com.sma.performance.entity.StudentAttendanceDetails;

public interface StaffAttendanceRepository extends JpaRepository<StaffAttendanceRequest, Long>{

	@Query(value="select * from staff_attendance where teacher_id=?1 and date= ?2", nativeQuery = true)
	List<StaffAttendanceRequest> getByStaffIdAndDate(Integer id, LocalDate date);

	@Query(value="select * from staff_attendance where teacher_id=?1", nativeQuery=true)
	List<StaffAttendanceRequest> findByStaffId(Integer staffId);
}
