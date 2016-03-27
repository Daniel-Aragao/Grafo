package swing_components.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import grafo.Grafo;
import listeners.IupdateInfo;
import swing_components.Frame;

public class MenuClique extends Menu{
	private JButton voltarPrincipal;
	private JButton selecionarCliques;

	private JTextField kCliques;

	private JComboBox<Grafo> grafos;

	private ArrayList<String> cliques;

	public MenuClique(Grafo[] grafos, IupdateInfo updateInfo) {
		super(grafos, updateInfo);

		voltarPrincipal = new JButton("Voltar");
		selecionarCliques = new JButton("Selecionar");

		kCliques = new JTextField(5);

		this.grafos = new JComboBox<Grafo>(grafos);

		cliques = new ArrayList<String>();

		kCliques.addKeyListener(Menu.digitOnlyAdapter());
		addActionListeners();

		menu.add(voltarPrincipal);
		menu.add(this.grafos);
		menu.add(kCliques);
		menu.add(selecionarCliques);
	}

	private void addActionListeners(){
		voltarPrincipal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateInfo.updateToMenuPrincipal();

			}
		});
		selecionarCliques.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int k = 0;
				try{
					k = Integer.parseInt(kCliques.getText());
				}catch(NumberFormatException erro){
					k = ((Grafo)grafos.getSelectedItem()).getNumVertices();
				}

				cliques_to_string(((Grafo)grafos.getSelectedItem()).getCliques(k));

				updateInfo();
			}
		});
	}

	protected void cliques_to_string(ArrayList<Grafo> _cliques) {
		cliques = new ArrayList<String>();
		for(Grafo g : _cliques){
			cliques.add(g.toString_Lista());
		}

	}

	protected void updateInfo() {
		updateInfo.updateTitle(Frame.titulo + " - " + grafo.getNome()+ " - Cliques");

		if(cliques != null){
			updateInfo.updateArrayText(cliques);
		}

		updateInfo.updatePack();
	}

}
