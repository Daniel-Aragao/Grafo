package grafo;

import java.util.ArrayList;

public class GrafoDirigido extends Grafo {

	public GrafoDirigido(String nome) {
		super(nome);
		resetLog();
	}
	@Override
	protected void resetLog(){
		log = new ArrayList<String>();
		log.add(nome);
		log.add("D");
		log.add("0");
	}

	@Override
	public boolean addAresta(int i, int j) {
		i--;
		j--;
		if (i < vertices.size() && j < vertices.size()) {
			Vertice a = vertices.get(i);
			Vertice b = vertices.get(j);

			a.addAresta(b);
			log.add((i+1)+","+(j+1));
			return true;

		}

		return false;
	}

	@Override
	public String getNome(){
		return nome;
	}

	@Override
	public int getNumArestas() {
		int nArestas = 0;

		nArestas = getGrau();

		return nArestas;
	}
}
