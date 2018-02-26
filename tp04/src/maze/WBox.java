package maze;

/**This class describes a wall in the maze.*/

public class WBox extends Box {
	public WBox(Maze M, int a, int b) {
		super(M, a, b);
	}
	/**@return false*/ 
	public boolean canGo() {
		return false;
	}
	
	/**@return "W"*/ 
	public String getLabel() {
		return "W";
	}
}