package swing_components;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public abstract class Resultado {
	protected JPanel panel;

	public Resultado(){
		panel = new JPanel();
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
//		panel.setPreferredSize(new Dimension(100,100));

	}

	public abstract void setResultado(String text);
	public abstract void setResultado(ArrayList<String> texts);

	public Component getView() {
		return panel;
	}

}
