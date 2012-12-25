/**
 * 
 */
package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.template.model.Customer;
import com.template.model.validator.CustomerValidator;

/**
 * Spring MVC Sample Controller
 * 
 */
@Controller
@RequestMapping("/simpleForm")
public class SimpleFormController {

	private CustomerValidator customerValidator;

	@Autowired
	public SimpleFormController(CustomerValidator customerValidator) {
		this.customerValidator = customerValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		Customer cust = new Customer();

		// command object
		model.addAttribute("customer", cust);

		// return form view
		return "simpleForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("customer") Customer customer,
			BindingResult result, SessionStatus status) {

		customerValidator.validate(customer, result);

		if (result.hasErrors()) {
			// if validator failed
			return "simpleForm";
		} else {
			// clear the command object from the session
			status.setComplete();
			// form success
			
			
			
			// return form success view
			return "simpleFormSuccess";
		}
	}

}
