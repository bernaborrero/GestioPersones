package com.bernabeborrero.gestiopersones;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingUtilities;

import com.bernabeborrero.gestiopersones.controllers.Controller;
import com.bernabeborrero.gestiopersones.models.DataControl;
import com.bernabeborrero.gestiopersones.views.AppView;
import com.bernabeborrero.gestiopersones.views.DialogView;

/**
 * GestioPersones is an application to manage personal information of persons
 * @author Bernabé Borrero
 * 
 */
public class GestioPersones {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					DataControl model = new DataControl("persones.csv");
					AppView view = new AppView("Gestió de Persones");
					Controller controller = new Controller(model, view);
					controller.control();
				} catch (FileNotFoundException e) {
					new DialogView("Error fatal", "La base de dades no s'ha pogut trobar", DialogView.ERROR);
				} catch (IOException e) {
					new DialogView("Error fatal", "S'ha produït un error al llegir la base de dades", DialogView.ERROR);
				}
			}
		});

	}

}
