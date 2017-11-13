package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {

	/** set value as the father of point vertex */
	public void setValue(VertexInterface vertex, VertexInterface value);

	/** get the value of vertex i, return null if not set */
	public VertexInterface getValue(VertexInterface i);

	/** Returns the shortest path from the root to a vertex */
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
}
