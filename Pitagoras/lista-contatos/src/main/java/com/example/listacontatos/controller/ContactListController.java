package com.example.listacontatos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import com.example.listacontatos.component.ContactComponent;
import com.example.listacontatos.models.Contact;
import com.example.listacontatos.repository.ContactRepository;

public class ContactListController {

	private final ContactRepository repository = ContactRepository.getInstance();

	@FXML
	private ListView<ContactComponent> contactListView;

	@FXML
	private TextField name;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField email;
	@FXML
	private TextField linkedin;

	@FXML
	protected void createNewContact() {
		if (getText(name) != null) {
			createContact();
		} else {
			showError();
		}
	}

	private void createContact() {
		Contact contact = new Contact(getText(name), getText(phoneNumber), getText(email), getText(linkedin));
		repository.add(contact);
		refreshContactListView();
		clear();
	}

	private void refreshContactListView() {
		contactListView.getItems().clear();
		for (Contact contact : repository.getAll()) {
			contactListView.getItems().add(new ContactComponent(contact));
		}
	}

	private void clear() {
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
		Contact contact = contactListView.getSelectionModel().getSelectedItem().getContact();
		if (contact != null) {
			name.setText(contact.getName());
			phoneNumber.setText(contact.getPhoneNumber());
			email.setText(contact.getEmail());
			linkedin.setText(contact.getLinkedin());
		}
	}
}
