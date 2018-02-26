package maze;

/**This class describes an empty box.*/

public class EBox extends Box {
	public EBox(Maze M, int a, int b) {
		super(M, a, b);
	}
	
	/**@return true*/ 
	public boolean canGo() {
		return true;
	}
	
	/**@return "E"*/ 
	public String getLabel() {
		return "E";
	}
}
