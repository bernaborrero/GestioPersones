package com.bernabeborrero.gestiopersones.views;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class for displaying dialogs
 * @author Bernabé Borrero
 *
 */
@SuppressWarnings("serial")
public class DialogView extends JPanel {
	
	/**
	 * Utility constants
	 */
	public static final int INFORMATION = JOptionPane.INFORMATION_MESSAGE;
	public static final int WARNING = JOptionPane.WARNING_MESSAGE;
	public static final int ERROR = JOptionPane.ERROR_MESSAGE;
	
	/**
	 * Display a dialog
	 * @param title title of the dialog
	 * @param message message of the dialog
	 * @param type type of icon
	 */
	public DialogView(String title, String message, int type) {
		JOptionPane.showMessageDialog(null, message, title, type);
	}
	
	/**
	 * Display a dialog with the title "Error"
	 * @param message
	 * @param type
	 */
	public DialogView(String message, int type) {
		this("Error", message, type);
	}

}
