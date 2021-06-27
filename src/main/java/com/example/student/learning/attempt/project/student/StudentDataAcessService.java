package com.example.student.learning.attempt.project.student;

import java.util.List;
import java.util.UUID;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class StudentDataAcessService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Student> getStudents(){
		String sql=""+
	                "SELECT "+
	                " student_id, "+
	                " first_name, "+
				    " last_name, "+
	                " gender, "+
				    " email "+
	                "FROM student";
		List<Student> students = jdbcTemplate.query(sql,
				new StudentMapper());
		  
		return students;
//		return List.of(new Student(UUID.randomUUID(),"james", "one", Student.Gender.MALE, "james.one@gmail.com"),
//				new Student(UUID.randomUUID(),"dan", "two", Student.Gender.MALE, "dan.two@gmail.com"),
//				new Student(UUID.randomUUID(),"lane", "one", Student.Gender.MALE, "lane.one@gmail.com"));
	}
	
	public List<Student_Course> getAllStudentCourses(UUID studentid){
		String sql=""+
	                 "SELECT * FROM student "
				     + "JOIN student_course using(student_id) "
	                 + "JOIN course using(course_id)"
				     +"WHERE student_id = ?";
		@SuppressWarnings("deprecation")
		List<Student_Course> student_courses = jdbcTemplate.query(sql,new Object[] {studentid},(rs,i) -> {
			return new Student_Course(UUID.fromString(rs.getString("student_id")),UUID.fromString(rs.getString("course_id")),
					rs.getDate("end_date"), rs.getDate("start_date"),  rs.getString("name"),rs.getString("teacher_name"),
					   rs.getString("department"),rs.getString("description"),rs.getInt("grade")
					);
		});
		return student_courses;
	}

	public int addNewStudent(UUID studentid, Student student) {
		// TODO Auto-generated method stub
		String sql = "" + "INSERT INTO student("+
		                 " student_id, "+
				         " first_name, "+
		                 " last_name, "+
				         " gender, "+
		                 " email) "+
		                 "VALUES (?, ?, ?, ?, ?::gender)";
		      return jdbcTemplate.update(sql,
		    		  studentid,
		    		  student.getFirstName(),
		    		  student.getLastName(),
		    		 student.getGender().name().toUpperCase(), 
		    		 student.getEmail());            
		
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean IsEmailExists(String email) {
		String sql = ""+  "SELECT EXISTS ( "+
				 "SELECT 1 FROM student "+
				 "WHERE email = ?)";
		 boolean bool = jdbcTemplate.queryForObject(sql,new Object[] {email},(resultSet,i) -> resultSet.getBoolean(1));
		 return bool;
	}
	
	
}