package tp04;

import java.util.ArrayList;

public class Maze implements GraphInterface {
	private int length;
	private int width;
	private ArrayList<ArrayList<VertexInterface>> labyrinthe;
	private int[][] matrixAjascente;

	public Maze(int a, int b) {
		length = a;
		width = b;
		// to be continued
	}

	public ArrayList<VertexInterface> getAllVertices() {
		// return all the boxes

		ArrayList<VertexInterface> temp = new ArrayList<VertexInterface>();

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++)
				temp.add(labyrinthe.get(i).get(j));
		}

		return temp;
	}

	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		// return all the boxes that this box has access to
		ArrayList<VertexInterface> temp = new ArrayList<VertexInterface>();

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++)
				if (matrixAjascente[i][j] == 1)
					temp.add(labyrinthe.get(i).get(j));
		}
		return temp;
	}
	
	public int getWeight(VertexInterface src,VertexInterface dst){
		if(matrixAjascente[])
		return 1;
	}
}