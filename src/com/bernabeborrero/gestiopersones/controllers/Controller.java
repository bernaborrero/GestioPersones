package com.bernabeborrero.gestiopersones.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;

import com.bernabeborrero.gestiopersones.exceptions.IllegalFormDataException;
import com.bernabeborrero.gestiopersones.exceptions.PersonAlreadyExistsException;
import com.bernabeborrero.gestiopersones.models.DataControl;
import com.bernabeborrero.gestiopersones.models.Person;
import com.bernabeborrero.gestiopersones.views.AppView;
import com.bernabeborrero.gestiopersones.views.DialogView;

/**
 * Controller of the application
 * @author Bernabé Borrero
 * 
 */
public class Controller {
	
	private DataControl model;
	private AppView view;
	private boolean isEditing, isNew;
	private Person currentPerson;
	
	/**
	 * Initialize the controller with the given model and view
	 * @param model main model of the application
	 * @param view main view of the application
	 */
	public Controller(DataControl model, AppView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Set up the controller's functioning
	 */
	public void control() {
		
		setWindowListeners(view.getFrame());
		
		// Set up registry count
		view.updateRegistryCount(model.getCurrentPersonID(), model.getTotalPersons());
		
		// Set up the first person data in the form
		updateFormFields(model.getNextPerson());
		
		// Set up buttons view
		isNew = false;
		isEditing = false;
		updateButtonsStatus();
		
		// Set up buttons' listeners
		addButtonsListeners();
	}
	
	/**
	 * Update the forms fields with a Person's information
	 * @param p the person to display
	 */
	private void updateFormFields(Person p) {
		view.setDNIField(p.getDni());
		view.setNameField(p.getName());
		view.setFirstSurnameField(p.getFirstSurname());
		view.setSecondSurname(p.getSecondSurname());
		view.setAge(Integer.toString(p.getAge()));
	}
	
	/**
	 * Update the buttons state (i.e. enabled / disabled)
	 */
	private void updateButtonsStatus() {
		
		if(model.isFirstElement()) {
			view.disableButton(view.getFirstItemButton());
			view.disableButton(view.getPreviousItemButton());
		} else {
			view.enableButton(view.getFirstItemButton());
			view.enableButton(view.getPreviousItemButton());
		}
		
		if(model.isLastElement()) {
			view.disableButton(view.getLastItemButton());
			view.disableButton(view.getNextItemButton());
		} else {
			view.enableButton(view.getLastItemButton());
			view.enableButton(view.getNextItemButton());
		}
		
		if(isNew || isEditing) {
			view.disableButton(view.getNewItemButton());
			view.disableButton(view.getDelItemButton());
			view.disableButton(view.getEditItemButton());
			view.disableButton(view.getFirstItemButton());
			view.disableButton(view.getPreviousItemButton());
			view.disableButton(view.getLastItemButton());
			view.disableButton(view.getNextItemButton());
			
			view.enableButton(view.getSaveButton());
			view.enableButton(view.getCancelButton());
		} else {
			view.enableButton(view.getNewItemButton());
			view.enableButton(view.getDelItemButton());
			view.enableButton(view.getEditItemButton());
			view.disableButton(view.getSaveButton());
			view.disableButton(view.getCancelButton());
		}
	}
	
	/**
	 * Attach the proper listeners to the buttons
	 */
	private void addButtonsListeners() {
		
		// New item button
		view.getNewItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onNewItemButtonPressed();
			}
		});
		
		// Delete item button
		view.getDelItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onDelItemButtonPressed();
			}
		});
		
		// Edit item button
		view.getEditItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onEditItemButtonPressed();
			}
		});
		
		// First item button
		view.getFirstItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onFirstItemButtonPressed();
			}
		});
		
		// Last item button
		view.getLastItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onLastItemButtonPressed();
			}
		});
		
		// Previous item button
		view.getPreviousItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onPreviousItemButtonPressed();
			}
		});
		
		// Next item button
		view.getNextItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onNextItemButtonPressed();
			}
		});
		
		// Save item button
		view.getSaveButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					onSaveItemButtonPressed();
				} catch (IllegalFormDataException | PersonAlreadyExistsException e1) {
					// Display error
					new DialogView(e1.getMessage(), DialogView.WARNING);
				}
				
			}
		});
		
		// Cancel item button
		view.getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onCancelEditButtonPressed();
			}
		});
		
	}
	
	/**
	 * Construct a Person object from the form information
	 * @return the constructed Person
	 */
	private Person formToPerson() {
		return new Person(
				view.getDNIField(),
				view.getNameField(),
				view.getFirstSurnameField(),
				view.getSecondSurname(),
				Integer.parseInt(view.getAge()));
	}
	
	/**
	 * New item button behavior
	 */
	private void onNewItemButtonPressed() {
		currentPerson = formToPerson();
		
		// Empty form text fields
		view.setDNIField("");
		view.setNameField("");
		view.setFirstSurnameField("");
		view.setSecondSurname("");
		view.setAge("");
		view.setEditableForm(true);
		
		// Update buttons
		isNew = true;
		updateButtonsStatus();
	}

	/**
	 * Delete item button behavior
	 */
	private void onDelItemButtonPressed() {
		model.deleteCurrentPerson();
		
		updateFormFields(model.getPreviousPerson());
		updateButtonsStatus();
		view.updateRegistryCount(model.getPreviousPersonID(), model.getTotalPersons());
	}

	/**
	 * Edit item button behavior
	 */
	private void onEditItemButtonPressed() {
		currentPerson = formToPerson();
		
		view.setEditableForm(true);
		
		// Update buttons
		isEditing = true;
		updateButtonsStatus();
	}
	
	/**
	 * Go to first item button behavior
	 */
	private void onFirstItemButtonPressed() {
		updateFormFields(model.getFirstPerson());
		updateButtonsStatus();
		view.updateRegistryCount(1, model.getTotalPersons());
	}
	
	/**
	 * Go go last item button behavior
	 */
	private void onLastItemButtonPressed() {
		updateFormFields(model.getLastPerson());
		updateButtonsStatus();
		view.updateRegistryCount(model.getTotalPersons(), model.getTotalPersons());
	}
	
	/**
	 * Go to previous item button behavior
	 */
	private void onPreviousItemButtonPressed() {
		updateFormFields(model.getPreviousPerson());
		updateButtonsStatus();
		view.updateRegistryCount(model.getPreviousPersonID(), model.getTotalPersons());
	}
	
	/**
	 * Go to next item button behavior
	 */
	private void onNextItemButtonPressed() {
		view.updateRegistryCount(model.getCurrentPersonID(), model.getTotalPersons());
		updateFormFields(model.getNextPerson());
		updateButtonsStatus();
	}
	
	/**
	 * Save item button behavior
	 */
	private void onSaveItemButtonPressed() throws IllegalFormDataException, PersonAlreadyExistsException {
		
		// Validate form data
		validateFormData();
		
		// Construct person
		Person p = formToPerson();

		if(isEditing) {
			// Update current person
			model.updateCurrentPerson(p);
			isEditing = false;
			
			updateFormFields(p);
			updateButtonsStatus();
			view.updateRegistryCount(model.getPreviousPersonID(), model.getTotalPersons());

		} else {
			// Create new person
			if(!model.saveNewPerson(p)) {
				// Person already exists, saving fails
				throw new PersonAlreadyExistsException("Aquesta persona ja existeix");
			}
			
			isNew = false;
			
			updateFormFields(model.getLastPerson());
			updateButtonsStatus();
			view.updateRegistryCount(model.getTotalPersons(), model.getTotalPersons());
		}
		
		view.setEditableForm(false);
	}

	
	/**
	 * Cancel editing button behavior
	 */
	private void onCancelEditButtonPressed() {
		isNew = false;
		isEditing = false;
		
		updateFormFields(currentPerson);
		updateButtonsStatus();
		
		view.setEditableForm(false);
	}
	
	/**
	 * Ensure that the form data is correct
	 * @throws IllegalFormDataException
	 */
	public void validateFormData() throws IllegalFormDataException {
		
		if(view.getDNIField().trim().length() == 0) {
			throw new IllegalFormDataException("El camp DNI no pot estar buit");
		}
		
		if(view.getNameField().trim().length() == 0) {
			throw new IllegalFormDataException("El camp Nom no pot estar buit");
		}
		
		if(view.getFirstSurnameField().trim().length() == 0) {
			throw new IllegalFormDataException("El camp Primer Cognom no pot estar buit");
		}
		
		if(view.getSecondSurname().trim().length() == 0) {
			throw new IllegalFormDataException("El camp Segon Cognom no pot estar buit");
		}
		
		if(view.getAge().trim().length() == 0) {
			throw new IllegalFormDataException("El camp Edat no pot estar buit");
		}
		else {
			try {
				int age = Integer.parseInt(view.getAge());
				if(age < 1 || age > 999) {
					throw new IllegalFormDataException("L'edat hauria d'estar entre 1 i 999");
				}
			} catch (NumberFormatException e) {
				throw new IllegalFormDataException("Sembla que el camp Edat no conté una edat vàlida");
			}
		}
	}
	
	/**
	 * Add listeners to the main frame of the view
	 * @param frame
	 */
	private void setWindowListeners(JFrame frame) {
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					model.saveDataToDisk();
				} catch (IOException e1) {
					new DialogView("Les dades no s'han pogut guardar a la base de dades", DialogView.WARNING);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}
			
		});
	}
	
}
