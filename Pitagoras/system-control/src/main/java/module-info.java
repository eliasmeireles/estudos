module com.softwareplace.systemcontrol {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.annotation;

	opens com.softwareplace.systemcontrol to javafx.fxml;
	exports com.softwareplace.systemcontrol;
	exports com.softwareplace.systemcontrol.domain.models;
	exports com.softwareplace.systemcontrol.controller;
	opens com.softwareplace.systemcontrol.controller to javafx.fxml;
	exports com.softwareplace.systemcontrol.component;
	opens com.softwareplace.systemcontrol.component to javafx.fxml;
}
