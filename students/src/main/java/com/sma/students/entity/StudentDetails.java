package com.sma.students.entity;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
public class StudentDetails {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "First name is required")
	    @Size(max = 50, message = "First name must not exceed 50 characters")
	    private String firstName;

	    @NotBlank(message = "Last name is required")
	    @Size(max = 50, message = "Last name must not exceed 50 characters")
	    private String lastName;

	    @NotBlank(message = "Date of birth is required")
	    @Past(message = "Date of birth must be in the past")
	    private LocalDate dateOfBirth;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Email should be valid")
	    private String email;

	    @NotBlank(message = "Contact number is required")
	    @Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
	    private String contactNumber;

	    @NotBlank(message = "Address is required")
	    @Size(max = 255, message = "Address must not exceed 255 characters")
	    private String address;

	    @NotBlank(message = "Grade is required")
	    @Pattern(regexp = "^(Grade [1-9]|Grade 1[0-2])$", message = "Grade must be between 'Grade 1' and 'Grade 12'")
	    private String grade;

	    @NotBlank(message = "Enrollment date is required")
	    @FutureOrPresent(message = "Enrollment date must be today or in the future")
	    private LocalDate enrollmentDate;
	    
	    private String admissionNumber;
}
