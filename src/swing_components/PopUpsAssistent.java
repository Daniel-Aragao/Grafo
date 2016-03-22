package swing_components;

import javax.swing.JOptionPane;

public class PopUpsAssistent{

	public static void exibirMenssagem(String retornoIO, int icon) {
		JOptionPane.showMessageDialog(null, retornoIO, "", icon);

	}

	public static String getInput(String caminho, int icon) {
		return JOptionPane.showInputDialog(null,caminho,"", icon);

	}


}
