package com.sma.staff.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sma.staff.entity.StaffDetails;

public interface StaffSaveRepository extends JpaRepository<StaffDetails, Integer>{
	
	@Query(value="select s.id from staff_details s", nativeQuery=true)
	List<Integer> findAllStaffIds();

}
