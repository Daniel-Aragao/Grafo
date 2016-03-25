package swing_components.menus;

import java.awt.BorderLayout;

import grafo.Grafo;
import grafo.GrafoDirigido;
import listeners.IupdateInfo;
import swing_components.Frame;
import swing_components.TextPanel;

public class Controller {

	private Frame frame;
	private Menu menu;
	private MenuPrincipal menuPrincipal;
	private MenuClique menuClique;
	private TextPanel resultado;

	IupdateInfo updateInfo;

	private Grafo[] grafos;
	private Grafo grafoSimples;
	private GrafoDirigido grafoDirigido;

	public Controller(){
		frame = new Frame();
		resultado = new TextPanel();

		updateInfo = new IupdateInfo() {

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
		frame.add(resultado.getResultado(), BorderLayout.CENTER);

		frame.setVisible(true);
		frame.pack();
	}
	private void menuChangeTo(Menu menu){
		frame.remove(this.menu.getMenu());
		this.menu = menu;
//		this.menu.updateInfo();
		frame.add(this.menu.getMenu(), BorderLayout.NORTH);
		frame.pack();
	}
}
//eventos:
//grafo change - mudar título, mudar texto, mudar label do menu
//vertice adicionado mudar texto
//aresta adicionada mudar texto
//remover all mudar texto
//importar
//exportar
//exibicao change mudar texto