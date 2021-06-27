package com.example.student.learning.attempt.project.student;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student{
	public final UUID studentId;
	public UUID getStudentId() {
		return studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	@Override
	public String toString() {
		return "Student {studentId="+studentId+" firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email
				+ "}";
	}
	public String getLastName() {
		return lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	
	enum Gender{
		MALE,FEMALE
	}
	public Student(@JsonProperty("studentId") UUID studentId, 
			@JsonProperty("firstName") String firstName, 
			@JsonProperty("lastName") String lastName, 
			@JsonProperty("gender") Gender gender, 
			@JsonProperty("email") String email) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}
	@NotBlank
	private final String firstName;
	@NotBlank
	private final String lastName;
	@NonNull
	private final Gender gender;
	@Email
	private final String email;
	
}