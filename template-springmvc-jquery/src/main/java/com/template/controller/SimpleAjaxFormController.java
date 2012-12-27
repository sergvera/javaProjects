/**
 * 
 */
package com.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.template.model.Customer;

/**
 * Spring MVC Sample Controller
 * 
 */
@Controller

public class SimpleAjaxFormController {

	@RequestMapping(value="/simpleAjaxForm",method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		Customer cust = new Customer();

		// command object
		model.addAttribute("customer", cust);

		// return form view
		return "simpleAjaxForm";
	}
	
	@RequestMapping(value="/postForm.json",method = RequestMethod.POST)
	public @ResponseBody
	GenericJSONResponse processSubmit(@ModelAttribute("customer") @Valid Customer customer,
			BindingResult bindingResult, SessionStatus status) {

		GenericJSONResponse result = new GenericJSONResponse();
		result.setResult(customer);

		if (!bindingResult.hasErrors()) {
			result.setStatus(GenericJSONResponse.STATUS.SUCCESS.toString());
		} else {
			result.setStatus(GenericJSONResponse.STATUS.VALIDATION_ERROR.toString());
			List<FieldError> allErrors = bindingResult.getFieldErrors();
			List<AjaxErrorMessage> errorMesages = new ArrayList<AjaxErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new AjaxErrorMessage(objectError.getField(), objectError.getField() + "  "
						+ objectError.getDefaultMessage()));
			}
			result.setErrorMessages(errorMesages);
		}

		return result;

	}

}
