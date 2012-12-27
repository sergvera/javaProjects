/**
 * 
 */
package com.template.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author serg
 * 
 */
public class GenericJSONResponse {

	public enum STATUS  {SUCCESS, FAIL, VALIDATION_ERROR};
	
	/*
	 * Could be success, fail, invalid, etc
	 */
	private String status = null;

	/**
	 * Stores error messages
	 */
	private List<AjaxErrorMessage> errorMessages = new ArrayList<AjaxErrorMessage> ();

	/*
	 * Stores the object created, if necessary
	 */
	private Object result = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
