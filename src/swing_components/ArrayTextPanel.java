package swing_components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class ArrayTextPanel extends Resultado{
	JScrollPane scroll;

	public ArrayTextPanel(){
		scroll = new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(600, 200));
	}

	@Override
	public void setResultado(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResultado(ArrayList<String> texts) {
		panel.removeAll();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));

		ArrayList<TextPanel> textPanels = new ArrayList<TextPanel>();

		for(String text : texts){
			TextPanel textPanel = new TextPanel();
			textPanel.setResultado(text);

			textPanel.setBoder(BorderFactory.createLineBorder(Color.BLACK));
			textPanels.add(textPanel);
			panel.add(textPanel.getView());
		}


	}
	@Override
	public Component getView() {
		return scroll;
	}

}
