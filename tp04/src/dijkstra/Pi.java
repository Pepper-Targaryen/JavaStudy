package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
	private Hashtable<VertexInterface, Integer> piPoints;

	public Pi(int numberOfVertex) {
		piPoints = new Hashtable<VertexInterface, Integer>(numberOfVertex);
	}

	public void setValue(VertexInterface vertex, int value) {
		piPoints.put(vertex, value);
	}

	public int getValue(VertexInterface vertex) {
		return piPoints.get(vertex).intValue();
	}
}
