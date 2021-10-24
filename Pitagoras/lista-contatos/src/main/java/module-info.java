module com.example.listacontatos {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires eu.hansolo.tilesfx;

	opens com.example.listacontatos to javafx.fxml;
	exports com.example.listacontatos;
	exports com.example.listacontatos.controller;
	opens com.example.listacontatos.controller to javafx.fxml;
}
