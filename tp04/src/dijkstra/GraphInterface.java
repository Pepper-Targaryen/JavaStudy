package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {
	
	public ArrayList<VertexInterface> getAllVertices();
	
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
	
	/** the distance between two points */
	public int getWeight(VertexInterface src, VertexInterface dst);
	
}
