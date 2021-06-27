package com.example.student.learning.attempt.project;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.example.student.learning.attempt.project.validations.Validator;

public class EmailValidator {

	private final Validator underTest = new Validator();
	@Test
	public void itShouldValidateCorrectEmail() {
		assertThat(underTest.test("abc2@gmail.com")).isTrue();
		}
	@Test
	public void itShouldInValidateCorrectEmail() {
		assertThat(underTest.test("abc2gmail.com")).isFalse();
		}

}
