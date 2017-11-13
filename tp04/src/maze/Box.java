package maze;

import dijkstra.VertexInterface;

public abstract class Box implements VertexInterface{
	private int x;
	private int y;
	private Maze labyrinthe;
	
	public Box(Maze M, int a, int b) {
		labyrinthe = M;
		x = a;
		y = b;
	}
	public abstract String getLabel();
	
	public abstract boolean canGo();
}
