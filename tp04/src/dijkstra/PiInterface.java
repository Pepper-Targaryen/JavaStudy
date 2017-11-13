package dijkstra;


public interface PiInterface {

	/** change the min distance between root and point i */
	public void setValue(VertexInterface i, int minDistance);
	
	/** get the min distance between root and point i */
	public int getValue(VertexInterface i);

}
