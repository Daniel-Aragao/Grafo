package swing_components.menus;

import java.awt.BorderLayout;
import java.util.ArrayList;

import grafo.Grafo;
import grafo.GrafoDirigido;
import listeners.IupdateInfo;
import swing_components.ArrayTextPanel;
import swing_components.Frame;
import swing_components.Resultado;
import swing_components.TextPanel;

public class Controller {

	private Frame frame;
	private Menu menu;
	private MenuPrincipal menuPrincipal;
	private MenuClique menuClique;

	private Resultado resultado;
	private Resultado textPanel;
	private Resultado arrayTextPanel;

	IupdateInfo updateInfo;

	private Grafo[] grafos;
	private Grafo grafoSimples;
	private GrafoDirigido grafoDirigido;

	public Controller(){
		frame = new Frame();

		textPanel = new TextPanel();
		arrayTextPanel = new ArrayTextPanel();
		resultado = textPanel;

		updateInfo = new IupdateInfo() {

			@Override
			public void updateTitle(String newTitle) {
				frame.setTitle(newTitle);
			}

			@Override
			public void updateText(String newText) {
				updateView(textPanel);
				resultado.setResultado(newText);
			}

			@Override
			public void updateArrayText(ArrayList<String> texts) {
				updateView(arrayTextPanel);
				resultado.setResultado(texts);
			}

			@Override
			public void updatePack() {
				frame.pack();
			}

			@Override
			public void updateToMenuPrincipal() {
				menuChangeTo(menuPrincipal);
			}

			@Override
			public void updateToMenuClique() {
				menuChangeTo(menuClique);

			}
		};

		grafoSimples = new Grafo("Grafo Simples");
		grafoDirigido = new GrafoDirigido("Grafo Dirigido");

		Grafo[] grafos = {grafoSimples, grafoDirigido};
		this.grafos = grafos;

		menuClique = new MenuClique(this.grafos, updateInfo);
		menuPrincipal = new MenuPrincipal(this.grafos, updateInfo);
		menu = menuPrincipal;

		frame.add(menu.getMenu(), BorderLayout.NORTH);
		frame.add(resultado.getView(), BorderLayout.CENTER);

		frame.setVisible(true);
		frame.pack();
	}
	private void menuChangeTo(Menu menu){
		if(this.menu != menu){

			frame.remove(this.menu.getMenu());

			this.menu = menu;
			frame.add(this.menu.getMenu(), BorderLayout.NORTH);

			this.menu.updateInfo();

			frame.pack();
		}
	}

	public void updateView(Resultado result) {
		if(resultado != result){
			frame.remove(this.resultado.getView());

			this.resultado = result;
			frame.add(this.resultado.getView(), BorderLayout.CENTER);

			frame.repaint();
		}
	}
}