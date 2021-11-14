module com.example.trabalhofinal {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires java.sql;
	requires mysql.connector.java;

	exports com.example.trabalhofinal.controller.delegate;
	opens com.example.trabalhofinal to javafx.fxml;
	exports com.example.trabalhofinal;
	exports com.example.trabalhofinal.controller;
	opens com.example.trabalhofinal.controller to javafx.fxml;
	exports com.example.trabalhofinal.util;
	exports com.example.trabalhofinal.view;
	exports com.example.trabalhofinal.view.component;

}
