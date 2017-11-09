package tp04;

public class DBox extends Box {
	public DBox(Maze M, int a, int b) {
		super(M, a, b);
	}

	public boolean canGo() {
		return true;
	}

	public String getLabel() {
		return "D";
	}
}
