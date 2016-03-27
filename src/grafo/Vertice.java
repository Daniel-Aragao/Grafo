package grafo;
import java.util.ArrayList;

public class Vertice {
	private ArrayList<Vertice> adjacencias;
	private int index;

	public Vertice(){
		adjacencias = new ArrayList<Vertice>();
	}

	public boolean addAresta(Vertice vertice){


		adjacencias.add(vertice);

		return true;
	}

	public boolean removeAresta(Vertice v){
		return adjacencias.remove(v);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<Vertice> getAdjacencias() {
		return adjacencias;
	}

	public int getGrau() {
		int grau = 0;

		grau = adjacencias.size();


		return grau;
	}

	public boolean contains(Vertice v) {
		return adjacencias.contains(v);
	}

	public int howMany(Vertice v){
		int x = 0;

		for(Vertice vertice : adjacencias){
			if(v == vertice){
				x++;
			}
		}

		return x;
	}


}
