package com.example.student.learning.attempt.project.student;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student_Course{
	private final UUID course_id;
	
	private final Date start_date;
	private final Date end_date;
    private final Integer grade;
	public Integer getGrade() {
		return grade;
	}
	private final String name;
	private final String description;
	private final String department;
	private final String teacher_name;
	
	public Student_Course(UUID course_id, UUID student_id, 
			Date start_date, Date end_date, String name, String description, 
			String department, String teacher_name,Integer grade) {
		super();
		this.course_id = course_id;
		
		this.start_date = start_date;
		this.end_date = end_date;
		this.grade = grade;
		this.name = name;
		this.description = description;
		this.department = department;
		this.teacher_name = teacher_name;
		
	}
	
	
	

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDepartment() {
		return department;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public UUID getCourse_id() {
		return course_id;
	}
	
	public Date getStart_date() {
		return start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	
}