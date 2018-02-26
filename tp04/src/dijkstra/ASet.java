package dijkstra;

import java.util.HashSet;

public class ASet implements ASetInterface {
	private HashSet<VertexInterface> points;


	public ASet() {
		points = new HashSet<VertexInterface>();
	}
	
	/**
	 * Adds a vertex to aSet
	 * @param vertex  vertex to add
	 */
	public void add(VertexInterface vertex) {
		points.add(vertex);
	}

	/**
	 * Checks if aSet contains a vertex
	 * @param vertex  vertex to check
	 * @return if the vertex is contained
	 */
	public boolean contains(VertexInterface vertex) {
		return points.contains(vertex);
	}
}
