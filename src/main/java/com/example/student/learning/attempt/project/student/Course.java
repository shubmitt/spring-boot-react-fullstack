package com.example.student.learning.attempt.project.student;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course{
	private final UUID course_id;
	@NotBlank
	private final String name;
	@NotBlank
	private final String department;
	
	private final String teacher_name;
	public Course(UUID course_id, String name, String department, String teacher_name, String description) {
		super();
		this.course_id = course_id;
		this.name = name;
		this.department = department;
		this.teacher_name = teacher_name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public String getDepartment() {
		return department;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	private final String description;
	public UUID getCourse_id() {
		return course_id;
	}
	public String getDescription() {
		return description;
	}
	
}