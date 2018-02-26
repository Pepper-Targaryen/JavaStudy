package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
	private Hashtable<VertexInterface, Integer> piPoints;
	
	/** 
	 * @param numberOfVertex  The number of vertex in the graph.
	 */
	public Pi(int numberOfVertex) {
		piPoints = new Hashtable<VertexInterface, Integer>(numberOfVertex);
	}
	
	/**
	 * Sets the shortest value from the root
	 * @param vertex vertex to set
	 * @param value value to set
	 */
	public void setValue(VertexInterface vertex, int value) {
		piPoints.put(vertex, value);
	}
	
	
	/**
	 * Gets the value of the shortest path from the root
	 * @param vertex vertex to handle
	 * @return the value of the shortest path from the root
	 */
	public int getValue(VertexInterface vertex) {
		return piPoints.get(vertex).intValue();
	}
}
