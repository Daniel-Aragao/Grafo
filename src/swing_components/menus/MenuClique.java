package swing_components.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import grafo.Grafo;
import listeners.IupdateInfo;
import swing_components.Frame;

public class MenuClique extends Menu{
	private JButton voltarPrincipal;

	public MenuClique(Grafo[] grafos, IupdateInfo updateInfo) {
		super(grafos, updateInfo);

		voltarPrincipal = new JButton("Voltar");

		addActionListeners();

		menu.add(voltarPrincipal);

		updateInfo();
	}

	private void addActionListeners(){
		voltarPrincipal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateInfo.updateToMenuPrincipal();

			}
		});
	}

	protected void updateInfo() {
		updateInfo.updateTitle(Frame.titulo + " - " + grafo.getNome()+ " - Cliques");

	}

}
