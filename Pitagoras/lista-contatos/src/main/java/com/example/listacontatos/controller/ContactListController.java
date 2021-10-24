package com.example.listacontatos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import com.example.listacontatos.component.ContactComponent;
import com.example.listacontatos.models.Contact;
import com.example.listacontatos.repository.ContactRepository;

public class ContactListController {

	private final ContactRepository repository = ContactRepository.getInstance();

	@FXML
	private ListView<ContactComponent> contactListView;

	@FXML
	private VBox emptyContactListAlert;

	@FXML
	private VBox contactListViewRoot;

	@FXML
	private TextField name;

	@FXML
	private TextField phoneNumber;

	@FXML
	private TextField email;

	@FXML
	private TextField linkedin;

	private Contact contact;

	@FXML
	protected void createNewContact() {
		if (getText(name) != null) {
			createContact();
		} else {
			showError();
		}
	}

	private void createContact() {
		if (contact != null) {
			updateContactInfo();
		} else {
			contact = new Contact();
			updateContactInfo();
			repository.add(contact);
		}
		resetController();
	}

	@FXML
	protected void resetController() {
		contact = null;
		refreshContactListView();
		clearTextFields();
	}

	private void updateContactInfo() {
		contact.setName(getText(name));
		contact.setPhoneNumber(getText(phoneNumber));
		contact.setEmail(getText(email));
		contact.setLinkedin(getText(linkedin));
	}

	private void refreshContactListView() {
		contactListView.getItems().clear();
		for (Contact c : repository.getAll()) {
			contactListViewRoot.getChildren().remove(emptyContactListAlert);
			contactListView.getItems().add(new ContactComponent(c));
		}
	}

	protected void clearTextFields() {
		name.clear();
		phoneNumber.clear();
		linkedin.clear();
		email.clear();
	}

	private void showError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Name field must be set!");
		alert.show();
		name.requestFocus();
	}

	private String getText(TextField textField) {
		String value = textField.getText().trim();
		if (!value.isEmpty()) {
			return value;
		}
		return null;
	}

	@FXML
	protected void selectedContact() {
		try {
			contact = contactListView.getSelectionModel().getSelectedItem().getContact();
			if (contact != null) {
				name.setText(contact.getName());
				phoneNumber.setText(contact.getPhoneNumber());
				email.setText(contact.getEmail());
				linkedin.setText(contact.getLinkedin());
			}
		} catch (Exception e) {
			// Ignore error
		}
	}
}
