package dijkstra;

import java.util.HashSet;

public class ASet implements ASetInterface {
	private HashSet<VertexInterface> points;

	public ASet() {
		points = new HashSet<VertexInterface>();
	}

	public void add(VertexInterface vertex) {
		points.add(vertex);
	}

	public boolean contains(VertexInterface vertex) {
		return points.contains(vertex);
	}
}
