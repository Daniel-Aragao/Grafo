package swing_components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{

	public static String titulo;

	public Frame() {

		titulo = "Matriz de Adjacências";

		this.setLayout(new BorderLayout());

		setFrame();
	}

	private void setFrame() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.black);
//		this.setSize(new Dimension(400, 100));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

	}

	public JFrame getFrame() {
		return this;
	}

}
