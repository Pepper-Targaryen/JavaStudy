package maze;


public class ABox extends Box {
	public ABox(Maze M, int a, int b) {
		super(M, a, b);
	}

	public boolean canGo() {
		return true;
	}

	public String getLabel() {
		return "A";
	}
}