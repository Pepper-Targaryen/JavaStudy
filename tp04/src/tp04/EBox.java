package tp04;

public class EBox extends Box {
	public EBox(Maze M, int a, int b) {
		super(M, a, b);
	}

	public boolean canGo() {
		return true;
	}

	public String getLabel() {
		return "E";
	}
}
