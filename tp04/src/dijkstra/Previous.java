package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

public class Previous implements PreviousInterface {
	private Hashtable<VertexInterface, VertexInterface> previousPoints;
	
	
	/**  
	 * @param numberOfVertex  The number of vertex in the graph.
	 */
	public Previous(int numberOfVertex) {
		previousPoints = new Hashtable<VertexInterface, VertexInterface>(numberOfVertex);
	}
	
	/**
	 * Sets a vertex value as the father
	 * @param vertex  vertex to set
	 * @param value  value to put 
	 */
	public void setValue(VertexInterface vertex, VertexInterface value) {
		previousPoints.put(vertex, value);
	}
	
	/**
	 * Gets previous Vertex from another one
	 * @param vertex  child vertex
	 * @return the father vertex (null if not set)
	 */
	public VertexInterface getValue(VertexInterface vertex) {
		return previousPoints.get(vertex);
	}
	
	/**
	 * Gets path between a vertex and root
	 * @param vertex  the last vertex of the path 
	 * @return the path
	 */
	
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) {
		ArrayList<VertexInterface> result = new ArrayList<VertexInterface>();
		VertexInterface temp = vertex;
		result.add(temp);
		while (this.getValue(temp) != null) {
			temp = this.getValue(temp);
			result.add(temp);
		}
		return result;
	}
}