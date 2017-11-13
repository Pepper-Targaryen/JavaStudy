package dijkstra;


public interface ASetInterface {

	/** add a point in the set */
	public void add(VertexInterface vertex);

	/** if contain point vertex */
	public boolean contains(VertexInterface vertex);
}
