package maze;

import dijkstra.VertexInterface;

/**This class describes a box that is also a vertex in a graph(maze)*/

public abstract class Box implements VertexInterface{
	
	protected int x;
	protected int y;
	protected Maze labyrinthe;
	
	
	
	public Box(Maze M, int a, int b) {
		labyrinthe = M;
		x = a;
		y = b;
	}
	
	/**
	 * Gets the line of the box.
	 * @return x the line in a two-dimensional array
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the column of the box.
	 * @return x  the column in a two-dimensional array
	 */
	public int getY() {
		return y;
	}

	
	/**
	 * Gets the coordinates of the box for MainTest.
	 * @return the coordinates of the box printable on the console
	 */
	public String getCoordinates() {
		return "x = "+x+", y = "+y;
	}
	
	/**
	 * Returns the matching letter to a box type.
	 * @return "A","D","E",or "W" depending on the type
	 */
	public abstract String getLabel();
	
	/**
	 * Checks if a box is reachable.
	 * @return true if it is, false otherwise
	 */
	public abstract boolean canGo();
	
}
