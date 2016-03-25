package swing_components.menus;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import IOutilities.IOManagement;
import grafo.Grafo;
import listeners.IupdateInfo;
import swing_components.Frame;
import swing_components.PopUpsAssistent;

public class MenuPrincipal extends Menu{


	private JButton resetGrafo;
	private JButton addVertice;
	private JButton addAresta;
	private JButton confirmarAction;
	private JButton importarGrafo;
	private JButton exportarGrafo;

	private JLabel qtdeV;

	private JTextField addAresta1;
	private JTextField addAresta2;

	private JComboBox<Grafo> comboGrafos;
	private JComboBox<String> opcoesExibicao;
	private JComboBox<JButton> actions;


	@SuppressWarnings("serial")
	public MenuPrincipal(Grafo[] grafos, IupdateInfo updateInfo){
		super(grafos, updateInfo);


		confirmarAction = new JButton("Confirmar");
		resetGrafo = new JButton("Remover todos"){@Override public String toString(){ return this.getText();}};
		addVertice = new JButton("+ Vertice"){@Override public String toString(){ return this.getText();}};
		importarGrafo = new JButton("Importar"){@Override public String toString(){ return this.getText();}};
		exportarGrafo = new JButton("Exportar"){@Override public String toString(){ return this.getText();}};
		addAresta = new JButton("+ Aresta ");

		addAresta1 = new JTextField(5);
		addAresta2 = new JTextField(5);

		comboGrafos = new JComboBox<Grafo>(grafos);

		String[] opcoes = {"Matriz", "Lista","Cliques", "Info"};
		opcoesExibicao = new JComboBox<String>(opcoes);

		JButton[] actionopcoes = {addVertice,resetGrafo, importarGrafo, exportarGrafo};
		actions = new JComboBox<JButton>(actionopcoes);

		qtdeV = new JLabel(grafo.getvCounter() + "");


		addAresta1.addKeyListener(Menu.digitOnlyAdapter());
		addAresta2.addKeyListener(Menu.digitOnlyAdapter());

		menu.setLayout(new FlowLayout(FlowLayout.LEFT));

		addActionListeners();

		//menu.add(removeV);
		//menu.add(addV);
		menu.add(actions);
		menu.add(confirmarAction);
		menu.add(qtdeV);

		menu.add(addAresta1);

		menu.add(addAresta2);
		menu.add(addAresta);

		menu.add(comboGrafos);
		menu.add(opcoesExibicao);
		//menu.add(result);
		updateInfo();
	}

	private void addActionListeners() {
		addVertice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				grafo.addVertice();
				updateInfo();
				updateInfo();
			}
		});
		resetGrafo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				grafo.reset();
				addAresta1.setText("");
				addAresta2.setText("");
				updateInfo();
			}
		});

		addAresta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try{
					int a = Integer.parseInt(addAresta1.getText());
					int b = Integer.parseInt(addAresta2.getText());

					if(grafo.addAresta(a, b)){
						addAresta1.setText("");
						addAresta2.setText("");
					}
					updateInfo();
				}catch(NumberFormatException nfe){
					nfe.printStackTrace();
				}

			}
		});
		comboGrafos.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				grafo = (Grafo) e.getItem();
				updateInfo();
			}
		});
		opcoesExibicao.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				updateInfo();
			}
		});

		confirmarAction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				((JButton)actions.getSelectedItem()).doClick();

			}
		});
		importarGrafo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String path = PopUpsAssistent.getInput("Arquivo: ", JOptionPane.WARNING_MESSAGE);
				if(path != null){
					Grafo grafoImportado = new Grafo("Grafo Importado");
					grafoImportado = IOManagement.ImportarGrafo(path);


					if(grafoImportado!= null){
						comboGrafos.addItem(grafoImportado);
						PopUpsAssistent.exibirMenssagem("Sucesso ao Importar de: "+ path,
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

			}
		});
		exportarGrafo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Grafo grafoExportado = grafo;
				String path = PopUpsAssistent.getInput("Caminho: ", JOptionPane.WARNING_MESSAGE);

				if(path != null){
					String nome = PopUpsAssistent.getInput("Nome do Grafo", JOptionPane.QUESTION_MESSAGE);
					if( nome != null){
						if(IOManagement.ExportarGrafo(grafoExportado, path, nome)){
							PopUpsAssistent.exibirMenssagem("Exportado com sucesso para: "+path,
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}

				updateInfo();
			}
		});

	}

	protected void updateInfo(){
		qtdeV.setText(""+grafo.getvCounter());

		updateInfo.updateTitle(Frame.titulo + " - " + grafo.getNome());

		String exibir = "";

		String opcao = opcoesExibicao.getSelectedItem().toString().toLowerCase();

		if(opcao.compareTo("matriz") == 0){
			exibir = grafo.toString_Matriz();
		}else if(opcao.compareTo("lista") == 0){
			exibir = grafo.toString_Lista();
		}else if(opcao.compareTo("info") == 0){
			exibir = grafo.toString_GrafoInfo();
		}else if(opcao.compareTo("cliques") == 0){
			updateInfo.updateToMenuClique();
			opcoesExibicao.setSelectedIndex(0);
		}

		updateInfo.updateText(exibir);

		updateInfo.updatePack();
	}

	public JPanel getMenu() {
		return menu;
	}

	public Grafo getGrafo() {
		return grafo;
	}
}
