package com.company.view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class TelaNotas implements View {
	private final TelaNotaListener listener;
	private final LabelTextField matriculaField;
	private final LabelTextField alunoField;
	private final LabelTextField codigoDiscilinaField;
	private final LabelTextField notaUm;
	private final LabelTextField notaDois;
	private final LabelTextField notaTres;
	private final LabelTextField conceito;
	private final Button calcular;
	private final Button pesquisar;
	private final Button limpar;
	private JPanel container;

	public TelaNotas(final TelaNotaListener listener) {
		this.listener = listener;
		matriculaField = new LabelTextField("Matrícula");
		alunoField = new LabelTextField("Aluno", new Dimension(255, 30));
		codigoDiscilinaField = new LabelTextField("Cod. Disciplina");
		notaUm = new LabelTextField("Nota 1");
		notaDois = new LabelTextField("Nota 2");
		notaTres = new LabelTextField("Nota 3");
		conceito = new LabelTextField("Conceito", new Dimension(255, 30));
		calcular = new Button("Calcular");
		pesquisar = new Button("Pesquisar");
		limpar = new Button("Limpar");

		limpar.addActionListener(listener::limpar);
		calcular.addActionListener(listener::calcular);
		pesquisar.addActionListener(listener::pesquisar);
	}

	@Override public JPanel getView() {
		container.setLayout(new GridBagLayout());
		JPanel firstPanel = new JPanel(new GridLayout(4, 1));
		firstPanel.add(matriculaField.getView());
		firstPanel.add(notaUm.getView());
		firstPanel.add(notaDois.getView());
		firstPanel.add(notaTres.getView());
		container.add(firstPanel);

		JPanel centerPanel = new JPanel(new GridLayout(4, 1));
		centerPanel.add(alunoField.getView());
		centerPanel.add(new JPanel());
		centerPanel.add(new JPanel());
		centerPanel.add(conceito.getView());
		container.add(centerPanel);

		JPanel rightPanel = new JPanel(new GridLayout(4, 1));
		rightPanel.add(codigoDiscilinaField.getView());
		rightPanel.add(buttonPanel(calcular));
		rightPanel.add(buttonPanel(pesquisar));
		rightPanel.add(buttonPanel(limpar));
		container.add(rightPanel);
		conceito.setEditable(false);
		return container;
	}

	private JPanel buttonPanel(Button button) {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		button.setMinimumSize(new Dimension(125, 40));
		button.setPreferredSize(new Dimension(125, 40));
		buttonPanel.add(button);
		return buttonPanel;
	}

	public LabelTextField getMatriculaField() {
		return matriculaField;
	}

	public LabelTextField getAlunoField() {
		return alunoField;
	}

	public LabelTextField getCodigoDiscilinaField() {
		return codigoDiscilinaField;
	}

	public LabelTextField getNotaUm() {
		return notaUm;
	}

	public LabelTextField getNotaDois() {
		return notaDois;
	}

	public LabelTextField getNotaTres() {
		return notaTres;
	}

	public LabelTextField getConceito() {
		return conceito;
	}

	public void limpar() {
		matriculaField.clean();
		alunoField.clean();
		codigoDiscilinaField.clean();
		notaUm.clean();
		notaDois.clean();
		notaTres.clean();
		conceito.clean();
	}

	public interface TelaNotaListener {
		void calcular(ActionEvent actionEvent);

		void pesquisar(ActionEvent actionEvent);

		void limpar(ActionEvent actionEvent);
	}
}
