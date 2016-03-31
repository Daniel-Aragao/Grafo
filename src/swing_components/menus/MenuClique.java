package swing_components.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import grafo.Grafo;
import listeners.IupdateInfo;
import swing_components.Frame;

public class MenuClique extends Menu{
	private JButton voltarPrincipal;
	private JButton selecionarCliques;

	private JTextField kCliques;

	private JComboBox<Grafo> grafos;

	private JLabel qtdeC;

	private ArrayList<String> cliques;

	public MenuClique(ArrayList<Grafo> grafos, IupdateInfo updateInfo) {
		super(grafos, updateInfo);

		voltarPrincipal = new JButton("Voltar");
		selecionarCliques = new JButton("Selecionar");

		qtdeC = new JLabel("0");

		kCliques = new JTextField(5);

		this.grafos = new JComboBox<Grafo>();
		for(Grafo g : grafos){
			this.grafos.addItem(g);
		}

		cliques = new ArrayList<String>();

		kCliques.addKeyListener(Menu.digitOnlyAdapter());
		addActionListeners();

		menu.add(voltarPrincipal);
		menu.add(this.grafos);
		menu.add(kCliques);
		menu.add(selecionarCliques);
		menu.add(qtdeC);
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
					k = 0;//((Grafo)grafos.getSelectedItem()).getNumVertices();
				}

				cliques = ((Grafo)grafos.getSelectedItem()).getCliques(k);

				updateInfo();
			}
		});
	}

	protected void updateInfo() {
		updateInfo.updateTitle(Frame.titulo + " - " + grafo.getNome()+ " - Cliques");

		if(cliques != null){
			updateInfo.updateArrayText(cliques);
		}

		qtdeC.setText(""+cliques.size());

		updateInfo.updatePack();
	}

}
