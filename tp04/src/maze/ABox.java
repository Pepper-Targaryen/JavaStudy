package maze;

/**This class describes a arrival box.*/

public class ABox extends Box {
	public ABox(Maze M, int a, int b) {
		super(M, a, b);
	}
	
	/**@return true*/ 
	public boolean canGo() {
		return true;
	}
	
	/**@return "A"*/ 
	public String getLabel() {
		return "A";
	}
}