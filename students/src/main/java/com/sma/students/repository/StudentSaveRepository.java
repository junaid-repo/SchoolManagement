package com.sma.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sma.students.entity.StudentDetails;

@Repository
public interface StudentSaveRepository extends JpaRepository<StudentDetails, Integer>{

	StudentDetails getByAdmissionNumber(String admitNumber);

}
