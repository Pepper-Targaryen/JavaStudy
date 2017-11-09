package tp04;

public class WBox extends Box {
	public WBox(Maze M, int a, int b) {
		super(M, a, b);
	}

	public boolean canGo() {
		return false;
	}

	public String getLabel() {
		return "W";
	}
}