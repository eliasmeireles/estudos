package com.example.listacontatos.component;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Collections;

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

	private static VBox build(Contact contact) {
		return new VBox(
				new Label(String.format("Name: %s", contact.getName())),
				new Label(String.format("Phone Number: %s", contact.getPhoneNumber())),
				new Label(String.format("E-mail: %s", contact.getEmail())),
				new Label(String.format("Linkedin: %s", contact.getLinkedin()))
		);
	}
}
