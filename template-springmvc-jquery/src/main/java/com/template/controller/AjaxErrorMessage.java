/**
 * 
 */
package com.template.controller;

/**
 * @author serg
 * 
 */
public class AjaxErrorMessage {

	private String fieldName;
	private String message;

	public AjaxErrorMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}

}
