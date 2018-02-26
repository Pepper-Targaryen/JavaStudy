package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import constant.LabyrinthModel;
import constant.WindowModel;

public class HelpPanel extends JPanel {
	static final long serialVersionUID = 30122017L;
	public final static int margin = LabyrinthModel.margin;
	
	/**
	 * Draws a string with several lines at the given position.
	 * 
	 * @param g
	 *            the graphics
	 * @param text
	 *            the instructions on screen
	 * @param x
	 *            the horizontal position
	 * @param y
	 *            the vertical position
	 */
	private void drawString(Graphics g, String text, int x, int y) {
		// Draw a string with several lines.
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(WindowModel.colorOfNotification); // Color of the Notification
		g.setFont(WindowModel.fontOfNotification); // Font
		drawString(g, "First, initialize a maze by generating a random maze"
				+ "\nor opening a text file that describes one."
				+ " \n\nIn the demontration mode : "
				+ "\nClick start and the path will be shown."	
				+ "\n\nIn the edit mode : \n1. You can create a labyrinth first."
				+ "\n2. Click the rectangle to change its type. "
				+ "\n3. Drag your mouse : black becomes white and viceversa.", margin, margin);
		
	}

}
