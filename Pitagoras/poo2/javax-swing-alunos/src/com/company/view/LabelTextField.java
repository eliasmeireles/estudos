package com.company.view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabelTextField implements View {
	private JPanel container;
	private JLabel label;
	private JTextField textField;

	public LabelTextField(final String labelText) {
		this.label.setText(labelText);
		textField.setMinimumSize(new Dimension(150, 30));
	}

	public LabelTextField(final String labelText, Dimension size) {
		this.label.setText(labelText);
		textField.setMinimumSize(size);
	}

	public void setLabel(String value) {
		label.setText(value);
	}

	public String getText() {
		return textField.getText().trim();
	}

	public void setText(String value) {
		textField.setText(value);
	}

	public void clean() {
		setText("");
	}

	@Override public JPanel getView() {
		return container;
	}

	public void setEditable(boolean value) {
		textField.setEnabled(value);
	}
}
