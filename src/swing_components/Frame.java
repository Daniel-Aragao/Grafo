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

import IOutilities.IOManagement;
import grafo.Grafo;
import grafo.GrafoDirigido;
import listeners.IupdateInfo;

public class Frame {
	private JFrame frame;

	private Menu menu;
	private TextPanel resultado;

	public Frame() {

		frame = new JFrame();

		frame.setLayout(new BorderLayout());

		menu = new Menu(new IupdateInfo() {

			@Override
			public void updateTitle(String newTitle) {
				frame.setTitle(newTitle);
			}

			@Override
			public void updateText(String newText) {
				resultado.setText(newText);

			}

			@Override
			public void updatePack() {
				frame.pack();

			}
		});
		resultado = new TextPanel();

		setFrame();
		frame.add(menu.getMenu(), BorderLayout.NORTH);
		frame.add(resultado.getResultado(), BorderLayout.CENTER);

		frame.setVisible(true);
		frame.pack();
	}

	private void setFrame() {
		frame.setTitle(Menu.titulo + " - " + menu.getGrafo().getNome());
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.black);
		// frame.setSize(new Dimension(900, 650));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

	}



// eventos:
//	 grafo change - mudar título, mudar texto, mudar label do menu
//	 vertice adicionado mudar texto
//	 aresta adicionada mudar texto
//	 remover all mudar texto
//	 importar
//	 exportar
//	 exibicao change mudar texto








}
