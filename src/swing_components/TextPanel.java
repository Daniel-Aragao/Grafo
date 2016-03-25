package swing_components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextPanel {
	private JPanel resultado;

	private JTextArea matrizAdjacencias;

	public TextPanel(){
		resultado = new JPanel();

		matrizAdjacencias = new JTextArea(10, 10);

		matrizAdjacencias.setEditable(false);

		resultado.setLayout(new BorderLayout());

		resultado.add(matrizAdjacencias);
	}

	public JPanel getResultado() {
		return resultado;
	}

	public void setText(String text){
		matrizAdjacencias.setText(text);
	}
}
