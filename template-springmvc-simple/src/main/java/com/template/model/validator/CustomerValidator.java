package com.template.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.template.model.Customer;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// just validate the Customer instances
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {

		Customer toValidate = (Customer) arg0;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"required.name", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Field name is required.");

		if (!toValidate.getPassword().equals("mypass")) {
			errors.rejectValue("password", "required.password",
					"Incorrect password");
		}

	}

}
