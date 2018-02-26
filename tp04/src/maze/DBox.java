package maze;

/**This class describes a departure box.*/

public class DBox extends Box {
	
	public DBox(Maze M, int a, int b) {
		super(M, a, b);
	}
	
	/**@return true*/ 
	public boolean canGo() {
		return true;
	}
	
	/**@return "D"*/ 
	public String getLabel() {
		return "D";
	}
}
