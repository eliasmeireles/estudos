package com.company;

import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LabelFrame extends JFrame {
	private final JLabel label1;
	private final JLabel label2;
	private final JLabel label3;

	public LabelFrame() {
		super("Testing JLabel");
		setLayout(new FlowLayout());

		// Construtor JLabel com um argumento de string
		label1 = new JLabel("Label with text");
		label1.setToolTipText("This is label1");
		add(label1); // adiciona o label1 ao JFrame

		// construtor JLabel com string, Icon e argumentos de alinhamento
		Icon bug = new ImageIcon(getClass().getResource("bug1.png"));
		label2 = new JLabel("Label with text and icon", bug, SwingConstants.LEFT);
		label2.setToolTipText("This is label2");
		add(label2); // adiciona label2 ao JFrame

		label3 = new JLabel(); // Construtor JLabel sem argumentos
		label3.setText("Label with icon and text at bottom");
		label3.setIcon(bug); // adiciona o ícone ao JLabel
		label3.setHorizontalTextPosition(SwingConstants.CENTER);
		label3.setVerticalTextPosition(SwingConstants.BOTTOM);
		label3.setToolTipText("This is label3");
		add(label3);
	}
}
