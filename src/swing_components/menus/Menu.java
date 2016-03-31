package swing_components.menus;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import grafo.Grafo;
import listeners.IupdateInfo;

public abstract class Menu {
	protected JPanel menu;

	protected Grafo grafo;
	protected ArrayList<Grafo> grafos;

	protected IupdateInfo updateInfo;

	public Menu(ArrayList<Grafo> grafos, IupdateInfo updateInfo){
		if(grafos.size() < 0 ){
			throw new RuntimeException("grafos.size() < 0");
		}
		menu = new JPanel();
		menu.setLayout(new FlowLayout(FlowLayout.LEFT));
//		menu.setBorder(BorderFactory.createEtchedBorder());

		this.grafos = grafos;
		grafo = grafos.get(0);

		this.updateInfo = updateInfo;
	}

	public JPanel getMenu() {
		return menu;
	}

	protected abstract void updateInfo();

	public static final KeyAdapter digitOnlyAdapter() {
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
