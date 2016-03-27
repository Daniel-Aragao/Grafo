package swing_components;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.border.Border;

public class TextPanel extends Resultado{

	private JTextArea matrizAdjacencias;

	public TextPanel(){
		super();

		matrizAdjacencias = new JTextArea(10, 10);

		matrizAdjacencias.setEditable(false);

		panel.setLayout(new BorderLayout());

		panel.add(matrizAdjacencias);
	}

	@Override
	public void setResultado(String text){
		matrizAdjacencias.setText(text);
	}

	@Override
	public void setResultado(ArrayList<String> texts) {
		// TODO Auto-generated method stub

	}

	public void setBoder(Border border){
		panel.setBorder(border);
	}
}
