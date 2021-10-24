package com.example.listacontatos.models;

public class Contact {

	private String name;
	private String phoneNumber;
	private String email;
	private String linkedin;

	public Contact() {
	}

	public Contact(String name, String phoneNumber, String email, String linkedin) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.linkedin = linkedin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	@Override public String toString() {
		return name;
	}
}
