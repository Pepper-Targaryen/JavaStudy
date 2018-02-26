package dijkstra;

/**This interface describes the pi function in Dijkstra's algorithm.*/
public interface PiInterface {

	/** Change the shortest distance between root and vertex i. */
	public void setValue(VertexInterface i, int minDistance);
	
	/** Get the shortest distance between root and vertex i. */
	public int getValue(VertexInterface i);

	
}
