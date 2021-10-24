module com.example.listacontatos {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	opens com.example.listacontatos to javafx.fxml;
	exports com.example.listacontatos;
	exports com.example.listacontatos.controller;
	opens com.example.listacontatos.controller to javafx.fxml;
	exports com.example.listacontatos.component;
	opens com.example.listacontatos.component to javafx.fxml;
}
