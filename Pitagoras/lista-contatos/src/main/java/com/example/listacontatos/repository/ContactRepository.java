package com.example.listacontatos.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.listacontatos.models.Contact;

public class ContactRepository {

	private static ContactRepository instace;
	private final List<Contact> contacts = new ArrayList<>();

	private ContactRepository() {
	}

	public static ContactRepository getInstance() {
		if (instace == null) {
			instace = new ContactRepository();
		}
		return instace;
	}

	public void add(Contact contact) {
		contacts.add(contact);
	}

	public List<Contact> getAll() {
		return contacts;
	}
}
