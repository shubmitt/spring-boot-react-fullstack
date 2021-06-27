
package com.example.student.learning.attempt.project.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {
   public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Student.Gender gender = Student.Gender.valueOf(rs.getString("gender").toUpperCase());
      Student student = new Student(UUID.fromString(rs.getString("student_id")), rs.getString("first_name"), rs.getString("last_name"), gender, 
    		  rs.getString("email"));
      
      
      return student;
   }
   
   
}