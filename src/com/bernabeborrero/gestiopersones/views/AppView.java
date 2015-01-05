package com.bernabeborrero.gestiopersones.views;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * Class to control the view of the application
 * @author Bernabé Borrero
 * 
 */
public class AppView {
	
	private JFrame frame;
	private JPanel menu, itemForm;
	private JButton newItemButton, delItemButton, editItemButton, firstItemButton, lastItemButton,
		nextItemButton, previousItemButton, saveButton, cancelButton;
	private JLabel registryCount;
	private JTextField dniField, nameField, surname1Field, surname2Field, ageField;
	
	/**
	 * Initialize the main window of the application with the given name
	 * @param title name of the application
	 */
	public AppView(String title) {
		
		// Create main frame
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1100, 500);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon("icon.png").getImage());
		
		// Create menu
		menu = new JPanel();
		addButtonsToMenu(menu);
		
		registryCount = new JLabel();
		menu.add(registryCount);
		updateRegistryCount(0, 0);
		frame.getContentPane().add(menu, BorderLayout.NORTH);
		
		// Create item form
		itemForm = new JPanel();
		SpringLayout formLayout = new SpringLayout();
		itemForm.setLayout(formLayout);
		
		addFormContents(itemForm, formLayout);
		setEditableForm(false);
		frame.getContentPane().add(itemForm);
		
		// Set the frame visible
		frame.setVisible(true);
	}
	
	/**
	 * Returns the main frame of this view
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Update the registry count
	 * @param current number of the current person
	 * @param total total number of persons
	 */
	public void updateRegistryCount(int current, int total) {
		registryCount.setText("Registre " + current + " de " + total);
	}
	
	/**
	 * Populate the menu
	 * @param menu
	 */
	private void addButtonsToMenu(JPanel menu) {
		
		// Item management buttons
		newItemButton = new JButton("Nou");
		menu.add(newItemButton);
		
		delItemButton = new JButton("Esborrar");
		menu.add(delItemButton);
		
		editItemButton = new JButton("Editar");
		menu.add(editItemButton);
		
		// Navigation buttons
		firstItemButton = new JButton("Primer");
		menu.add(firstItemButton);
		
		lastItemButton = new JButton("Últim");
		menu.add(lastItemButton);
		
		previousItemButton = new JButton("Anterior");
		menu.add(previousItemButton);
		
		nextItemButton = new JButton("Següent");
		menu.add(nextItemButton);
		
		// Permanence buttons
		saveButton = new JButton("Desar");
		menu.add(saveButton);
		
		cancelButton = new JButton("Cancel·lar");
		menu.add(cancelButton);
	}

	/**
	 * Populate the form
	 * @param itemForm
	 * @param formLayout
	 */
	private void addFormContents(JPanel itemForm, SpringLayout formLayout) {
		JLabel dniLabel, nameLabel, surname1Label, surname2Label, ageLabel;
		
		// DNI part
		dniLabel = new JLabel("DNI:");
		itemForm.add(dniLabel);
		formLayout.putConstraint(SpringLayout.WEST, dniLabel, 150, SpringLayout.WEST, itemForm);
		formLayout.putConstraint(SpringLayout.NORTH, dniLabel, 45, SpringLayout.NORTH, itemForm);
		
		dniField = new JTextField(40);
		itemForm.add(dniField);
		formLayout.putConstraint(SpringLayout.WEST, dniField, 120, SpringLayout.WEST, dniLabel);
		formLayout.putConstraint(SpringLayout.NORTH, dniField, 40, SpringLayout.NORTH, itemForm);
		
		// Name part
		nameLabel = new JLabel("Nom:");
		itemForm.add(nameLabel);
		formLayout.putConstraint(SpringLayout.WEST, nameLabel, 150, SpringLayout.WEST, itemForm);
		formLayout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, dniLabel);
		
		nameField = new JTextField(40);
		itemForm.add(nameField);
		formLayout.putConstraint(SpringLayout.WEST, nameField, 120, SpringLayout.WEST, dniLabel);
		formLayout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, dniField);
		
		// First surname part
		surname1Label = new JLabel("Primer cognom:");
		itemForm.add(surname1Label);
		formLayout.putConstraint(SpringLayout.WEST, surname1Label, 150, SpringLayout.WEST, itemForm);
		formLayout.putConstraint(SpringLayout.NORTH, surname1Label, 40, SpringLayout.NORTH, nameLabel);
		
		surname1Field = new JTextField(40);
		itemForm.add(surname1Field);
		formLayout.putConstraint(SpringLayout.WEST, surname1Field, 120, SpringLayout.WEST, dniLabel);
		formLayout.putConstraint(SpringLayout.NORTH, surname1Field, 40, SpringLayout.NORTH, nameField);
		
		// Second surname part
		surname2Label = new JLabel("Segon cognom:");
		itemForm.add(surname2Label);
		formLayout.putConstraint(SpringLayout.WEST, surname2Label, 150, SpringLayout.WEST, itemForm);
		formLayout.putConstraint(SpringLayout.NORTH, surname2Label, 40, SpringLayout.NORTH, surname1Label);
		
		surname2Field = new JTextField(40);
		itemForm.add(surname2Field);
		formLayout.putConstraint(SpringLayout.WEST, surname2Field, 120, SpringLayout.WEST, dniLabel);
		formLayout.putConstraint(SpringLayout.NORTH, surname2Field, 40, SpringLayout.NORTH, surname1Field);
		
		// Age part
		ageLabel = new JLabel("Edat:");
		itemForm.add(ageLabel);
		formLayout.putConstraint(SpringLayout.WEST, ageLabel, 150, SpringLayout.WEST, itemForm);
		formLayout.putConstraint(SpringLayout.NORTH, ageLabel, 40, SpringLayout.NORTH, surname2Label);
		
		ageField = new JTextField(40);
		itemForm.add(ageField);
		formLayout.putConstraint(SpringLayout.WEST, ageField, 120, SpringLayout.WEST, dniLabel);
		formLayout.putConstraint(SpringLayout.NORTH, ageField, 40, SpringLayout.NORTH, surname2Field);
	}
	
	/**
	 * Disable a button
	 * @param button
	 */
	public void disableButton(JButton button) {
		button.setEnabled(false);
	}
	
	/**
	 * Enable a button
	 * @param button
	 */
	public void enableButton(JButton button) {
		button.setEnabled(true);
	}
	
	/**
	 * Enable or disable the entire form
	 * @param editable true if the form is editable, false if not
	 */
	public void setEditableForm(boolean editable) {
		if(editable) {
			dniField.setEnabled(true);
			nameField.setEnabled(true);
			surname1Field.setEnabled(true);
			surname2Field.setEnabled(true);
			ageField.setEnabled(true);
		} else {
			dniField.setEnabled(false);
			nameField.setEnabled(false);
			surname1Field.setEnabled(false);
			surname2Field.setEnabled(false);
			ageField.setEnabled(false);
		}
	}
	
	// Setters
	public void setDNIField(String dni) {
		this.dniField.setText(dni);
	}
	
	public void setNameField(String name) {
		this.nameField.setText(name);
	}
	
	public void setFirstSurnameField(String firstSurname) {
		this.surname1Field.setText(firstSurname);
	}
	
	public void setSecondSurname(String secondSurname) {
		this.surname2Field.setText(secondSurname);
	}
	
	public void setAge(String age) {
		this.ageField.setText(age);
	}

	// Getters
	public String getDNIField() {
		return this.dniField.getText();
	}
	
	public String getNameField() {
		return this.nameField.getText();
	}
	
	public String getFirstSurnameField() {
		return this.surname1Field.getText();
	}
	
	public String getSecondSurname() {
		return this.surname2Field.getText();
	}
	
	public String getAge() {
		return this.ageField.getText();
	}
	
	public JButton getNewItemButton() {
		return newItemButton;
	}

	public JButton getDelItemButton() {
		return delItemButton;
	}

	public JButton getEditItemButton() {
		return editItemButton;
	}

	public JButton getFirstItemButton() {
		return firstItemButton;
	}

	public JButton getLastItemButton() {
		return lastItemButton;
	}

	public JButton getNextItemButton() {
		return nextItemButton;
	}

	public JButton getPreviousItemButton() {
		return previousItemButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
