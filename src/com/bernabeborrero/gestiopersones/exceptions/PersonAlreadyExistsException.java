package com.bernabeborrero.gestiopersones.exceptions;

/**
 * Exception to ensure that one person is only contained once
 * @author Bernabé Borrero
 *
 */
@SuppressWarnings("serial")
public class PersonAlreadyExistsException extends Exception {
	
	/**
	 * Constructor to store a message
	 * @param message
	 */
	public PersonAlreadyExistsException(String message) {
		super(message);
	}

}
