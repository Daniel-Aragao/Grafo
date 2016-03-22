package grafo;
import java.util.ArrayList;

public class Grafo {
	protected ArrayList<Vertice> vertices;
	protected String nome;
	protected ArrayList<String> log;

	public Grafo(String nome) {
		vertices = new ArrayList<Vertice>();
		this.nome = nome;
		resetLog();
	}
	protected void resetLog(){
		log = new ArrayList<String>();
		log.add(nome);
		log.add("S");
		log.add("0");
	}

	public void setNome(String nome) {
		this.nome = nome;
		log.set(0, nome);
	}

	public int getGrau() {
		int grau = 0;

		for (Vertice v : vertices) {
			grau += v.getGrau();
		}

		return grau;
	}

	public boolean addVertice() {
		Vertice vertice = new Vertice();
		if (vertices.add(vertice)) {

			return true;
		}
		return false;
	}

//	public Vertice removeVertice(int index) {
//		Vertice toRemove = null;
//		if (index <= vertices.size()) {
//			if (contains(index)) {
//				for (Vertice v : vertices) {
//					if (index == v.getIndex()) {
//						toRemove = v;
//						break;
//					}
//				}
//			}
//		}
//		removeFromAll(toRemove);
//		return toRemove;
//	}
//	private void removeFromAll(Vertice toRemove){
//		for(Vertice v : vertices){
//			if(v.contains(toRemove)){
//				v.removeAresta(toRemove);
//			}
//		}
//	}

	public boolean contains(int index) {
		if (getVertice(index) != null) {
			return true;
		}
		return false;
	}

	private Vertice getVertice(int index) {
		return vertices.get(index);
	}

	public int[][] getMatrizDeAdjacencias() {
		int[][] matriz = new int[vertices.size()][vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			Vertice v = vertices.get(i);
			for (int j = 0; j < vertices.size(); j++) {
				Vertice vJ = vertices.get(j);
				if (v.contains(vJ)) {
					matriz[i][j] = v.howMany(vJ);
				}
			}
		}

		return matriz;
	}
	public boolean addAresta(int i, int j){
		i--;
		j--;
		if(i < vertices.size() && j < vertices.size()){
			Vertice a = vertices.get(i);
			Vertice b = vertices.get(j);

			a.addAresta(b);
			b.addAresta(a);
			log.add((i+1)+","+(j+1));
			return true;
		}

		return false;
	}
	public String toString_Matriz(){
		int[][] m = getMatrizDeAdjacencias();
		String retorno = "";

			for(int i = 0; i < m.length; i++){
				for(int j = 0; j < m[0].length; j++){
					retorno += m[i][j]+" ";
				}
				retorno +="\n";
			}
			return retorno;


	}

	public int[][] getMatrizDeGrau(){
		int[][] matrizDeGrau = new int[3][vertices.size()];
		for(int i = 0; i < matrizDeGrau[0].length; i++){
			Vertice v = vertices.get(i);
			matrizDeGrau[0][i] = i+1;
			matrizDeGrau[1][i] = v.getGrau();
			matrizDeGrau[2][i] = getGrauEntrada(v);

		}

		return matrizDeGrau;
	}

	public int getGrauEntrada(Vertice vertice) {
		int j = 0;

		for(Vertice v : vertices){
			if(v.contains(vertice)){
				j+= v.howMany(vertice);
			}
		}

		return j;
	}

	public void reset() {
		this.vertices =  new ArrayList<Vertice>();
		resetLog();
	}

	public int getvCounter() {
		return vertices.size();
	}

	public String getNome() {
		return nome;
	}

	public String toString_GrafoInfo() {
		String stringMatrizDeGrau = "";
		if(!vertices.isEmpty()){
			String linhas[] = {"Vértices:         ", "Grau Entrada:", "Grau Saída:   "};
			int[][] matrizDeGrau = getMatrizDeGrau();

			for(int i = 0; i < matrizDeGrau.length; i++){
				stringMatrizDeGrau += linhas[i];
				for(int j = 0 ; j < matrizDeGrau[i].length; j++){
					stringMatrizDeGrau += matrizDeGrau[i][j]+" ";
				}
				stringMatrizDeGrau +="\n";
			}
			stringMatrizDeGrau += "\nArestas: "+getNumArestas();
			stringMatrizDeGrau += "\nGrau: "+getGrau();
			stringMatrizDeGrau += "\nCompleto: "+isCompleto();
			stringMatrizDeGrau += "\nConexo: "+isConexo();
			stringMatrizDeGrau += "\nBipartido: "+isBipartido();
		}
		return stringMatrizDeGrau;
	}

	public int getNumArestas() {
		int nArestas = 0;

		nArestas = getGrau()/2;

		return nArestas;
	}

	public int getVerticeIndex(Vertice v){
		return vertices.indexOf(v);
	}

	public String toString(){
		return getNome();
	}

	public int[][] getListaAdjacencias(){
		int [][] matriz = new int[vertices.size()][];

		for(int i = 0; i < matriz.length; i++){
			Vertice vi = vertices.get(i);
			int[] lista = new int[vi.getAdjacencias().size()];
			matriz[i] = lista;
			for(int j = 0; j < matriz[i].length; j++){
				matriz[i][j] = getVerticeIndex(vi.getAdjacencias().get(j));
			}
		}

		return matriz;
	}
	public String toString_Lista(){
		int[][] m = getListaAdjacencias();
		String retorno = "";

			for(int i = 0; i < m.length; i++){
				retorno += (i+1)+"=> ";
				for(int j = 0; j < m[i].length; j++){
					retorno += ((m[i][j]+1)+" ");
				}
				retorno +="\n";
			}
			return retorno;
	}

	public boolean isBipartido(){
		if(vertices.isEmpty()) return false;

		ArrayList<Vertice> A = new ArrayList<Vertice>();
		ArrayList<Vertice> B = new ArrayList<Vertice>();
		Vertice inicial = vertices.get(0);
		A.add(inicial);

		for(Vertice v : vertices){
			if(v != inicial){
				if(inicial.contains(v)){
					B.add(v);
				}else{
					A.add(v);
				}
			}
		}

		for(int i = 0 ; i < A.size() - 1; i++){
			Vertice primeiro = A.get(i);
			Vertice segundo = A.get(i+1);
			if(primeiro.contains(segundo))return false;
			if(segundo.contains(primeiro))return false;
		}

		for(int i = 0 ; i < B.size() - 1; i++){
			Vertice primeiro = B.get(i);
			Vertice segundo = B.get(i+1);
			if(primeiro.contains(segundo)) return false;
			if(segundo.contains(primeiro))return false;
		}


		return true;
	}

	public boolean isCompleto(){

		for(Vertice v1 : vertices){
			for(Vertice v2 : vertices){
				if(v1!= v2){
					if(!v1.contains(v2)){
						return false;
					}
				}
			}
		}

		return true;
	}

	public boolean isConexo(){
		for(Vertice v : vertices){
			if(v.getGrau() < 1) return false;
		}
		return true;
	}

	public ArrayList<String> getGrafoLog() {
		log.set(2, vertices.size()+"");
		return log;
	}


}
