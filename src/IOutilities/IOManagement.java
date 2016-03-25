package IOutilities;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import grafo.Grafo;
import grafo.GrafoDirigido;
import swing_components.PopUpsAssistent;

public class IOManagement {
	public static final String formatoCorreto = "Arquivo em formato incorreto"
			+ "\nNome\nS/D(tipo)\nNumero de Vertices"
			+ "\nVertice,Aresta\nVertice,Aresta,Aresta";
	public static Grafo ImportarGrafo(String path){
		Grafo g = null;

		ArrayList<String> linhas = FileImportExport.txtImport(path);
		if(linhas == null) return null;
		if(linhas.size() < 4){
			PopUpsAssistent.exibirMenssagem(formatoCorreto, JOptionPane.ERROR_MESSAGE);
			return null;
		}
		try{

			switch(linhas.get(1).toLowerCase().charAt(0)){
				case 's':
					g = new Grafo(linhas.get(0));
					break;
				case 'd':
					g = new GrafoDirigido(linhas.get(0));
					break;
			}
			int nVertices = Integer.parseInt(linhas.get(2));
			for(int i = 0; i < nVertices; i++){
				g.addVertice();
			}

			for(int i = 3; i < linhas.size(); i++){
				String linha = linhas.get(i);
				String[] linhaInfo = linha.split(",");
				int linhaV = Integer.parseInt(linhaInfo[0]);
				for(int j = 1; j < linhaInfo.length; j++){
					int vertice = Integer.parseInt(linhaInfo[j]);
					g.addAresta(linhaV, vertice);
				}
			}
		}catch(Exception e){
			PopUpsAssistent.exibirMenssagem(formatoCorreto, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return g;
	}

	public static boolean ExportarGrafo(Grafo g, String path, String nome){

		g.setNome(nome);

		return FileImportExport.txtExport(path + "\\" + nome + ".txt", g.getGrafoLog());
	}
}
