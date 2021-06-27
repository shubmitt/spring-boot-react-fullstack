package com.example.student.learning.attempt.project.validations;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Validator implements Predicate<String>{

	public static final Predicate<String> VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).asPredicate();

		

		@Override
		public boolean test(String t) {
			// TODO Auto-generated method stub
			return VALID_EMAIL_ADDRESS_REGEX.test(t);
		}
}
