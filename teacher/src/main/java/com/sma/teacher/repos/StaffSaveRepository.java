package com.sma.teacher.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sma.teacher.entity.StaffDetails;

public interface StaffSaveRepository extends JpaRepository<StaffDetails, Integer>{

}
