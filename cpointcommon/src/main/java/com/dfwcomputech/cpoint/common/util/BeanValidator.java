package com.dfwcomputech.cpoint.common.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.dfwcomputech.cpoint.common.CPointException;

public class BeanValidator {
	
	public <B> void validateBean(B bean) throws CPointException{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<B>> violations = validator.validate(bean);
		if(!violations.isEmpty())
			throw new CPointException(violations.iterator().next().getMessage());
	}

}
