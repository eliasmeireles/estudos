package com.company.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.company.controller.TelaNotasController;

public class MainView {
	public final JFrame mainFrame;
	private JPanel rootContainer;

	public MainView(String title) {
		this.mainFrame = new JFrame(title);
		TelaNotasController telaNotasController = new TelaNotasController();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(580, 420));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.add(telaNotasController.getView());
		mainFrame.setVisible(true);
	}

	public JPanel getRootContainer() {
		return rootContainer;
	}
}
