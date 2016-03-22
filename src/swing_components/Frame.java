package swing_components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import grafo.Grafo;
import grafo.GrafoDirigido;
import imports.IOManagement;

public class Frame {
	private JFrame frame;

	private JPanel menu;
	private JPanel resultado;

	private JButton resetGrafo;
	private JButton addVertice;
	private JButton addAresta;
	private JButton confirmarAction;
	private JButton importarGrafo;
	private JButton exportarGrafo;

	private JLabel qtdeV;

	private JTextField addAresta1;
	private JTextField addAresta2;

	private JTextArea matrizAdjacencias;

	private JComboBox<Grafo> comboGrafos;
	private JComboBox<String> opcoesExibicao;
	private JComboBox<JButton> actions;

	private Grafo grafo;
	private GrafoDirigido grafoDirigido;
	private Grafo grafoSimples;

	private String titulo;

	@SuppressWarnings("serial")
	public Frame() {

		frame = new JFrame();
		grafoSimples = new Grafo("Grafo Simples");
		grafoDirigido = new GrafoDirigido("Grafo Dirigido");
		grafo = grafoSimples;

		frame.setLayout(new BorderLayout());

		menu = new JPanel();
		resultado = new JPanel();

		addAresta1 = new JTextField(5);
		addAresta2 = new JTextField(5);

		confirmarAction = new JButton("Confirmar");
		resetGrafo = new JButton("Remover todos"){@Override public String toString(){ return this.getText();}};
		addVertice = new JButton("+ Vertice"){@Override public String toString(){ return this.getText();}};
		importarGrafo = new JButton("Importar"){@Override public String toString(){ return this.getText();}};
		exportarGrafo = new JButton("Exportar"){@Override public String toString(){ return this.getText();}};
		addAresta = new JButton("+ Aresta ");

		Grafo[] comboGrafosOpcoesIniciais = {grafoSimples, grafoDirigido};
		comboGrafos = new JComboBox<Grafo>(comboGrafosOpcoesIniciais);

		String[] opcoes = {"Matriz", "Lista", "Info"};
		opcoesExibicao = new JComboBox<String>(opcoes);

		JButton[] actionopcoes = {addVertice,resetGrafo, importarGrafo, exportarGrafo};
		actions = new JComboBox<JButton>(actionopcoes);

		qtdeV = new JLabel(grafo.getvCounter() + "");

		matrizAdjacencias = new JTextArea(10, 10);

		matrizAdjacencias.setEditable(false);

		addAresta1.addKeyListener(this.digitOnlyAdapter());
		addAresta2.addKeyListener(this.digitOnlyAdapter());

		menu.setLayout(new FlowLayout(FlowLayout.LEFT));
		resultado.setLayout(new BorderLayout());

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
		resultado.add(matrizAdjacencias);

		addActionListeners();

		titulo = "Matriz de Adjacências";
		setFrame();
		frame.add(menu, BorderLayout.NORTH);
		frame.add(resultado, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.pack();
	}

	private void setFrame() {
		frame.setTitle(titulo + " - " + grafo.getNome());
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.black);
		// frame.setSize(new Dimension(900, 650));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

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
				matrizAdjacencias.setText(grafo.toString());
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

	private void updateInfo(){
		qtdeV.setText(""+grafo.getvCounter());

		frame.setTitle(titulo + " - " + grafo.getNome());

		String exibir = "";

		String opcao = opcoesExibicao.getSelectedItem().toString().toLowerCase();

		if(opcao.compareTo("matriz") == 0){
			exibir = grafo.toString_Matriz();
		}else if(opcao.compareTo("lista") == 0){
			exibir = grafo.toString_Lista();
		}else if(opcao.compareTo("info") == 0){
			exibir = grafo.toString_GrafoInfo();
		}

		matrizAdjacencias.setText(exibir);

		frame.pack();
	}


	private KeyAdapter digitOnlyAdapter() {
		KeyAdapter a = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.') {
					e.consume();
				}
			}
		};

		return a;
	}



}
