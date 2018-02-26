package dijkstra;

/**This interface describes the model of the boxes in a maze.*/

public interface VertexInterface {
	
	/**Gets the label of a vertex (box in a maze)*/
	public String getLabel();
	
	/**Indicates if there is a link to that vertex (box in a maze)*/
	public abstract boolean canGo();
	
	
}
