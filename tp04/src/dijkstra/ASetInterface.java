package dijkstra;

/**This describes a set of vertices*/

public interface ASetInterface {

	/** Adds a point in the set. */
	public void add(VertexInterface vertex);

	/** if contain point vertex.*/
	public boolean contains(VertexInterface vertex);
}
