package com.example.student.learning.attempt.project.student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService{
	
	@Autowired
	private StudentDataAcessService studentDataAcessService;
	public List<Student> getAllStudents(){
		return studentDataAcessService.getStudents();
	}
	public void addNewStudent(Student student) {
		 addStudent(null,student);
		
	}
	
	public List<Course> getCourseDetails(UUID studentId) {
		List<Student_Course> details = studentDataAcessService.getAllStudentCourses(studentId);
		return details.stream().map(detail -> new Course(detail.getCourse_id(),detail.getDescription(),detail.getName(),
				detail.getDepartment(),detail.getTeacher_name())).collect(Collectors.toList());
	}
	
	public Boolean emailExists(String email) {
		return studentDataAcessService.IsEmailExists(email);
	}
	 void addStudent(UUID studentId, Student student) {
		// TODO Auto-generated method stub
		UUID studentid = Optional.ofNullable(studentId).orElse(UUID.randomUUID());
		
		studentDataAcessService.addNewStudent(studentid,student);
	}
}