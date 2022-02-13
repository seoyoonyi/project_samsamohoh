package com.server.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, Enum<?>> {

	private Pattern pattern;

	@Override
	public void initialize(EnumPattern annotation) {
		try {
			pattern = Pattern.compile(annotation.regexp()); // 정규식을 패턴 객체로 만들어줌.

		} catch (PatternSyntaxException e) {
			throw new IllegalArgumentException("pattern regex is invalid", e);
		}

	}

	@Override
	public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
		if (value == null)
			return false;
		
		Matcher m = pattern.matcher(value.name());
		
		return m.matches();
	}

}
