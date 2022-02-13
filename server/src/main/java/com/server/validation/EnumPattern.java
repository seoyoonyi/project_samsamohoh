package com.server.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = EnumPatternValidator.class)
public @interface EnumPattern {
	
	String regexp();
	
	String message() default "올바르지 않은 카테고리 값 입니다,";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};
}
