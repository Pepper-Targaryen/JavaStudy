package constant;

import java.awt.Color;


public class LabyrinthModel {
	
	/**By default. It can be modified because of resizing in the frame*/
	public static final int boxSize = 52;
	
	/**Minimum size of box(visible on the screen)*/
	public static final int minBoxSize = 2;
	
	/**Margin to display instructions*/
	public final static int margin = 20;
	
	/**Color dark beige for the arrival*/
	public final static Color colorOfABox = new Color(255, 204, 153);
	
	/**Color blue for the starting point*/
	public final static Color colorOfDBox = new Color(0, 153, 204);
	
	/**Color black for the wall*/
	public final static Color colorOfWBox = new Color(102, 102, 153);
	
	/**Color light beige for the reachable points*/
	public final static Color colorOfEBox = new Color(255, 255, 204);
	
	/**Color green for the displayed path*/
	public final static Color colorOfPath = new Color(204, 255, 153);
	

}
