package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

import constant.LabyrinthModel;
import constant.WindowModel;
import maze.*;

/** A class for editing the labyrinth by clicking. */

public class GameMirrorPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	static final long serialVersionUID = 30122017L;

	private MainWindowFrame mainWindowFrame;
	private Maze maze = null;
	private int boxSize = LabyrinthModel.boxSize;
	public final static int margin = LabyrinthModel.margin;

	/**
	 * @param mainWindowFrame
	 *            the window frame
	 */

	public GameMirrorPanel(MainWindowFrame mainWindowFrame) {
		this.mainWindowFrame = mainWindowFrame;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void resetBoxSize() {
		boxSize = LabyrinthModel.boxSize;
	}

	/**
	 * Sets and paints the maze to display
	 * 
	 * @param maze
	 *            the maze
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;

		// The size of the window adjusts the labyrinth
		if (maze != null) {
			setPreferredSize(null);
			setPreferredSize(
					new Dimension(margin * 2 + maze.getWidth() * boxSize, margin * 2 + maze.getLength() * boxSize));
		} else {
			setPreferredSize(null);
			// Warning! The first parameter is the width.
			setPreferredSize(new Dimension(MainWindowFrame.screenWidth / 2, MainWindowFrame.screenHeight / 2));
		}
		repaint();
	}
	
	
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
	
	/** 
	 * Draws a box at a specific position.
	 * @param g
	 *            the graphics
	 * @param box
	 *            the box to draw
	 * @param x
	 *            the horizontal position
	 * @param y
	 *            the vertical position
	 */
	private void drawBox(Graphics g, Box box, int x, int y) {
		String s = null;
		int fontWidth = 0;
		g.setFont(new Font("SansSerif", Font.PLAIN, boxSize - 1));
		switch (box.getLabel()) {
		case "E":
			g.setColor(LabyrinthModel.colorOfEBox);
			break;
		case "W":
			g.setColor(LabyrinthModel.colorOfWBox);
			break;
		case "D":
			g.setColor(LabyrinthModel.colorOfDBox);
			s = "D";
			fontWidth = g.getFontMetrics().getWidths()[68];
			break;
		case "A":
			g.setColor(LabyrinthModel.colorOfABox);
			s = "A";
			fontWidth = g.getFontMetrics().getWidths()[65];
			break;

		}
		g.fillRect(x, y, boxSize, boxSize);
		try {
			g.setColor(Color.BLACK);

			g.drawString(s, x + boxSize / 2 - fontWidth / 2,
					y + boxSize - 1 + boxSize / 2 - g.getFontMetrics().getHeight() / 2);
		} catch (NullPointerException e) {
			return;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (maze == null) {
			g.setColor(WindowModel.colorOfNotification); // Color of the Notification
			g.setFont(WindowModel.fontOfNotification); // Font
			drawString(g, "1. Please create a labyrinth first.\n2. Drag your mouse and draw your walls.\n3. Have fun!", margin, margin);
		} else {
			int numberOfHeight = maze.getLength(), numberOfWidth = maze.getWidth();
			for (int i = 0; i < numberOfHeight; i++) {
				for (int j = 0; j < numberOfWidth; j++) {
					// x is the horizontal axis, y is the vertical axis.
					// i is vertically increasing, j is vertically increasing.
					drawBox(g, maze.getBox(i, j), margin + j * boxSize, margin + i * boxSize);
				}
			}
			g.setColor(WindowModel.colorOfNotification); // Color of the Notification
			g.setFont(WindowModel.fontOfNotification); // Font
			g.drawString("Click the rectangle to change its type.", margin, margin + numberOfHeight * boxSize + g.getFontMetrics().getHeight());
		}
	}
	

	/**
	 * Resizes the box if the dimensions are too high.
	 * 
	 * @param height
	 *            the height of the maze
	 * @param width
	 *            the width of the maze
	 * @param screenHeight
	 *            the screen height
	 * @param screenWidth
	 *            the screen width
	 */
	public void resizeBox(int height, int width, int screenHeight, int screenWidth) {
		this.resetBoxSize();
		while (boxSize * height > screenHeight - margin * 5) {
			boxSize /= 2;
		}

		while (boxSize * width > screenWidth - margin * 2) {
			boxSize /= 2;
		}
	}

	/**Allows you to switch to another type of box.*/
	@Override
	public final void mouseClicked(MouseEvent e) {
		if (maze != null) {
			int j = (e.getX() - margin) / boxSize; // horizontal
			int i = (e.getY() - margin) / boxSize; // vertical
			if ((e.getX() - margin) > 0 && i < maze.getLength() && (e.getY() - margin) > 0 && j < maze.getWidth()) {
				switch (maze.getBox(i, j).getLabel()) {
				case "W":
					maze.setBox(i, j, new EBox(maze, i, j));
					break;
				case "E":
					DBox src = new DBox(maze, i, j);
					maze.setBox(i, j, src);
					maze.getSrc().add(src);
					break;
				case "D":
					maze.getSrc().remove(maze.getBox(i, j));
					ABox dst = new ABox(maze, i, j);
					maze.setBox(i, j, dst);
					maze.getDst().add(dst);
					break;
				case "A":
					maze.getDst().remove(maze.getBox(i, j));
					maze.setBox(i, j, new WBox(maze, i, j));
					break;
				}
				mainWindowFrame.setMaze(maze);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	

	/**Another way to remove or add wall by dragging the mouse*/
	@Override
	public void mouseDragged(MouseEvent e) {
		if (maze != null) {
			int j = (e.getX() - margin) / boxSize; // horizontal
			int i = (e.getY() - margin) / boxSize; // vertical
			if ((e.getX() - margin) > 0 && i < maze.getLength() && (e.getY() - margin) > 0 && j < maze.getWidth()) {
				switch (maze.getBox(i, j).getLabel()) {
				case "W":
					maze.setBox(i, j, new EBox(maze, i, j));
					break;
				case "E":
					maze.setBox(i, j, new WBox(maze, i, j));
					break;
				default :
					break;
				}
				mainWindowFrame.setMaze(maze);
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}
}
