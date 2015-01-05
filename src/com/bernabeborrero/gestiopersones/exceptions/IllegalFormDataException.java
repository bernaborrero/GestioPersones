package com.bernabeborrero.gestiopersones.exceptions;

/**
 * Exception to ensure that a form data is correct
 * @author Bernabé Borrero
 *
 */
@SuppressWarnings("serial")
public class IllegalFormDataException extends Exception {
	
	/**
	 * Constructor to store a message
	 * @param message
	 */
	public IllegalFormDataException(String message) {
		super(message);
	}

}
