package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

public class Previous implements PreviousInterface {
	private Hashtable<VertexInterface, VertexInterface> previousPoints;

	public Previous(int numberOfVertex) {
		previousPoints = new Hashtable<VertexInterface, VertexInterface>(
				numberOfVertex);
	}

	public void setValue(VertexInterface vertex, VertexInterface value) {
		previousPoints.put(vertex, value);
	}

	public VertexInterface getValue(VertexInterface vertex) {
		return previousPoints.get(vertex);
	}

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
