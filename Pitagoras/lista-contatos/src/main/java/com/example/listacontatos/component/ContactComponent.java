package com.example.listacontatos.component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import com.example.listacontatos.models.Contact;

public class ContactComponent extends VBox {

	private final Contact contact;

	public ContactComponent(Contact contact) {
		super(build(contact));
		this.contact = contact;
	}

	public Contact getContact() {
		return contact;
	}

	private static HBox[] build(Contact contact) {
		List<HBox> labels = new ArrayList<>();
		addContactInfo(labels, "Name", contact.getName());

		if (contact.getPhoneNumber() != null) {
			addContactInfo(labels, "Phone Number", contact.getPhoneNumber());
		}

		if (contact.getEmail() != null) {
			addContactInfo(labels, "E-mail", contact.getEmail());
		}
		if (contact.getLinkedin() != null) {
			addContactInfo(labels, "Linkedin", contact.getLinkedin());
		}

		return labels.toArray(new HBox[0]);
	}

	private static void addContactInfo(List<HBox> labels, String labelValue, String textValue) {
		if (textValue != null) {
			Label label = new Label(String.format("%s:", labelValue));
			label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #0a0a0a;");

			Label value = new Label(textValue);
			value.setStyle("-fx-font-size: 14px; -fx-text-fill: #3f3d3d;");

			HBox hBox = new HBox(label, value);
			hBox.setSpacing(5);
			hBox.setAlignment(Pos.CENTER_LEFT);
			labels.add(hBox);
		}
	}

}
