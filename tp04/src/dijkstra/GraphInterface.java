package dijkstra;

import java.util.ArrayList;

/** This interface describes a Graph. 
 * Dijkstra's algorithm uses this graph in order to store all vertex. 
 */

public interface GraphInterface {
	
	
	/**Gets all Vertex.*/
	public ArrayList<VertexInterface> getAllVertices();
	
	/** Gets linked Vertices to vertex*/
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
	
	/** Gets the distance between two points. */
	public int getWeight(VertexInterface src, VertexInterface dst);
	
}
