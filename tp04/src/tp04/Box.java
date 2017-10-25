package tp04;

public abstract class Box implements VertexInterface{
	private int x;
	private int y;
	private String label;
	private Maze labyrinthe;
	
	public Box(int a, int b) {
		x = a;
		y = b;
	}
	public final String getLabel(){
		return label;
	}
}
