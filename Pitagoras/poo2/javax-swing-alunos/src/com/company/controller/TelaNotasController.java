package com.company.controller;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import com.company.model.Nota;
import com.company.view.TelaNotas;

public class TelaNotasController implements TelaNotas.TelaNotaListener {

	private final TelaNotas telaNotas;

	public TelaNotasController() {
		this.telaNotas = new TelaNotas(this);
	}

	public JPanel getView() {
		return telaNotas.getView();
	}

	@Override public void calcular(ActionEvent actionEvent) {
		var notaUmString = telaNotas.getNotaUm().getText();
		var notaDoisString = telaNotas.getNotaDois().getText();
		var notaTresString = telaNotas.getNotaTres().getText();
		if (isValid(notaDoisString, notaUmString, notaTresString)) {
			try {
				double notaUm = Double.parseDouble(notaUmString);
				double notaDois = Double.parseDouble(notaDoisString);
				double notaTres = Double.parseDouble(notaTresString);

				var media = (notaUm + notaDois + notaTres) / 3;
				telaNotas.getConceito()
						.setText(String.format("Média: %.2f, conceito: %s", media, Nota.getNota(media)));
			} catch (Exception ex) {
				Toolkit.getDefaultToolkit().beep();
				ex.printStackTrace();
			}
		}
	}

	@Override public void pesquisar(ActionEvent actionEvent) {

	}

	@Override public void limpar(ActionEvent actionEvent) {
		telaNotas.limpar();
	}

	private boolean isValid(String... values) {
		boolean isValid = Arrays.stream(values)
				.filter(String::isBlank)
				.collect(Collectors.toCollection(Stack::new))
				.isEmpty();

		if (!isValid) {
			Toolkit.getDefaultToolkit().beep();
		}
		return isValid;

	}
}
