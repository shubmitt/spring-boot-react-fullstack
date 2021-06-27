package com.example.student.learning.attempt.project.student;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.learning.attempt.project.exception.ApiRequestException;
import com.example.student.learning.attempt.project.student.Student.Gender;
import com.example.student.learning.attempt.project.validations.Validator;

@RestController
@RequestMapping("/student")
public class StudentController{
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private Validator validator;
	
	@GetMapping("/getAll")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
		//throw new IllegalStateException("Cannot find any student");
		
		//to throw custom exception:
		 //throw new ApiRequestException("message");
				
	}
	
	@GetMapping("/getCourses/{studentId}")
	public List<Course> getAllCourses(@PathVariable UUID studentId){
		return studentService.getCourseDetails(studentId);
	}
	
	@PostMapping
	public void addNewStudent(@RequestBody @Valid Student student) {
		if(!validator.test(student.getEmail())) {
			throw new ApiRequestException("Invalid Email format");
		}
		if(studentService.emailExists(student.getEmail())) {
			throw new ApiRequestException(" Email already exists");
		}
		studentService.addNewStudent(student);
		
	}
	
}