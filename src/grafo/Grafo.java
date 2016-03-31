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

	public boolean addVertice(Vertice vertice){
		if(vertices != null){
			if(vertices.add(vertice)){
				return true;
			}
		}
		return false;
	}

	public Vertice removeVertice(int index) {
		Vertice toRemove = vertices.get(index);

		for(Vertice v : vertices){
			while(v.contains(toRemove)){
				removeAresta(vertices.indexOf(v),index);
				v.removeAresta(toRemove);
			}
		}

		vertices.remove(toRemove);

		return toRemove;
	}

	public Vertice removeVertice(Vertice toRemove) {
		for(Vertice v : vertices){
			while(v.contains(toRemove)){
				removeAresta(vertices.indexOf(v),vertices.indexOf(toRemove));
				v.removeAresta(toRemove);
			}
		}

		vertices.remove(toRemove);

		return toRemove;
	}
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
	public boolean removerAresta(int i, int j){
		i--;
		j--;
		return removeAresta(i,j);
	}

	protected boolean removeAresta(int i, int j){
		if(i < vertices.size() && j < vertices.size() && i >= 0 && j >= 0){
			Vertice v1 = vertices.get(i);
			Vertice v2 = vertices.get(j);

			if(v1.contains(v2) && v2.contains(v1)){
				v1.removeAresta(v2);
				v2.removeAresta(v1);

				removeFromLog(i+1, j+1);
				return true;
			}

		}
		return false;
	}

	protected void removeFromLog(int i, int j){
		int index = -1;

		int busca = 0;
		for(String s : log){
			if(s.equals(i+","+j)){
				index = busca;
			}
			busca++;
		}

		if(index < log.size() && index >= 0)
		log.remove(index);
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

	public int getNumVertices() {
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
			stringMatrizDeGrau += "\nRegular: "+isRegular();
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

	public int[][] getListasAdjacencias(){
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
		int[][] m = getListasAdjacencias();
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
		for(int j = 0 ; j < vertices.size();j++){
				ArrayList<Vertice> A = new ArrayList<Vertice>();
				ArrayList<Vertice> B = new ArrayList<Vertice>();
				Vertice inicial = vertices.get(j);
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
				boolean stillBipartido = true;
				for(int i = 0 ; i < A.size() - 1; i++){
					Vertice primeiro = A.get(i);
					Vertice segundo = A.get(i+1);
					if(primeiro.contains(segundo)||segundo.contains(primeiro))stillBipartido = false;
				}

				for(int i = 0 ; i < B.size() - 1; i++){
					Vertice primeiro = B.get(i);
					Vertice segundo = B.get(i+1);
					if(primeiro.contains(segundo)||segundo.contains(primeiro))stillBipartido = false;
				}
				if(stillBipartido) return true;
		}

		return false;
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

	public ArrayList<String> getCliques(int k){
		int[][] listaAdj = getListasAdjacencias();
		ArrayList<String> listas = new ArrayList<String>();

		for(int i = 0; i < listaAdj.length; i++){
			ArrayList<int[]> cliques = new ArrayList<int[]>();
			int [] cliqueInicial = new int[listaAdj.length];
			doValue(cliqueInicial,-1);
			cliqueInicial[0] = i;
			cliques.add(cloneVetor(cliqueInicial));

			for(int j = 0; j < listaAdj.length; j++){
				int size = cliques.size();
				for(int p = 0; p < size; p++){
					int[] clique = cliques.get(p);
					if( j!=i &&allHave(listaAdj, clique, j)){
//						listas.add(toString_Lista_Clique(cliqueToListaAdjacencias(clique)));
						cliques.add(cloneVetor(clique));
						clique[getFinalVetor(clique,-1)] = j;
					}
				}
			}

			for(int[] clique : cliques){
				if(getFinalVetor(clique, -1) >= k)
				listas.add(toString_Lista_Clique(cliqueToListaAdjacencias(clique)));
			}
		}

		return listas;
	}
	private int getFinalVetor(int[] a, int b) {
		for(int i = 0; i < a.length; i++){
			if(a[i]==b){
				return i;
			}
		}
		return a.length;
	}
	private int[] cloneVetor(int[]a){
		int[] b = new int[a.length];

		for(int i = 0; i < a.length; i++)b[i] = a[i];

		return b;
	}
	private void doValue(int[]v, int e){
		for(int i = 0; i < v.length; i++){
			v[i] = e;
		}
	}
	private boolean allHave(int[][]a,int[]b, int e){
		for(int i = 0; i < b.length; i++){
			int x = b[i];
			if(x != -1){
				boolean controle = false;
				if(a[x]!= null){
					for(int j = 0; j < a[x].length;j++){
						if(a[x][j] == e){
							controle = true;
						}
					}
				}

				if(!controle) return false;
			}
		}

		return true;
	}
	private int[][] cliqueToListaAdjacencias(int[] clique){


		int[][] lista = new int[vertices.size()][];

		for(int i = 0; i < clique.length; i++){
			int x = clique[i];
			if(x!= -1){
				doValue(lista[x] = new int[clique.length],-1);
				for(int j = 0; j < clique.length; j++){
					if(x != clique[j])lista[x][j] = clique[j];
				}
			}
		}

		return lista;
	}
	private String toString_Lista_Clique(int[][] m){
		String retorno = "";

		for(int i = 0; i < m.length; i++){
			if(m[i] != null){
				retorno += (i+1)+"=> ";
				for(int j = 0; j < m[i].length; j++){
					if(m[i][j]!=-1)
						retorno += ((m[i][j]+1)+" ");
				}
				retorno +="\n";
			}
		}
		return retorno;
	}



//	public Grafo getCliqueMaximal(){
//		Grafo maximal = new Grafo("Maximal");
//
//		maximal.addVertice(getVertice(0));
//		for(int i = 1; i < vertices.size(); i++){
//			Vertice vIterator = vertices.get(i);
//
//			boolean allHave = true;
//			for(Vertice v : maximal.vertices){
//				if(!v.contains(vIterator) || !vIterator.contains(v)){
//					allHave = false;
//				}
//			}
//
//			if(allHave){
//				maximal.addVertice(vIterator);
//			}
//		}
//
//		return maximal;
//	}
	public String toString_Log() {
		String sLog = "";

		for(String s : getGrafoLog()){
			sLog +=s + "\n";
		}

		return sLog;
	}

	public boolean isRegular(){
		int[][] matriz = getMatrizDeAdjacencias();
		int[] graus = new int[matriz.length];

		for(int i = 0; i < matriz.length; i++){
			for(int j = 0; j < matriz[i].length; j++ ){
				graus[i] += matriz[i][j];
			}
		}
		for(int j = 1; j < graus.length; j++ ){
			if(graus[j] != graus[j-1]) return false;
		}
		return true;
	}


}
