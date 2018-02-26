package dijkstra;

import java.util.ArrayList;

/**This interface describes the father function in Dijkstra's algorithm.*/
public interface PreviousInterface {

	/** Sets value as the father of vertex. */
	public void setValue(VertexInterface vertex, VertexInterface value);

	/** Gets the value of vertex i, return null if not set. */
	public VertexInterface getValue(VertexInterface i);

	/** Returns one of the shortest path from the root to a vertex. */
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
}
