package com.company;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.company.controller.MainController;

public class Main {

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(MainController::new);
	}
}
