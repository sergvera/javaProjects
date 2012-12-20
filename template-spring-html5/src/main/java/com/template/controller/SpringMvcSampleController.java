/**
 * 
 */
package com.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC Sample Controller
 * 
 */
@Controller
public class SpringMvcSampleController {

	@RequestMapping("/helloWorld")
	public ModelAndView helloWorld() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("helloWorld");
		mav.addObject("message", "Hello World!");
		return mav;
	}
}
