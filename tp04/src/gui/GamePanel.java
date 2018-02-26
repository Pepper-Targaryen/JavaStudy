package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import constant.LabyrinthModel;
import constant.WindowModel;
import dijkstra.Dijkstra;
import dijkstra.VertexInterface;
import exceptions.MainFunctionsException;
import maze.Box;
import maze.Maze;

/** A class for the demonstration of the algorithm. */

public class GamePanel extends JPanel implements ActionListener {

	static final long serialVersionUID = 30122017L;

	private Timer timer = new Timer(100, this);
	private MainWindowFrame mainWindowFrame;
	private Maze maze = null;
	private boolean pathFlag = false;
	private ArrayList<VertexInterface> shortestPath;
	private int currentIterationOfPath = 0;
	private boolean arrived=false;

	private int boxSize = LabyrinthModel.boxSize;
	public static final int margin = LabyrinthModel.margin;
	

	public GamePanel(MainWindowFrame mainWindowFrame) {
		this.mainWindowFrame = mainWindowFrame;

	}

	/** Set back to the constant box size. */
	public void resetBoxSize() {
		boxSize = LabyrinthModel.boxSize;
	}

	/**
	 * Gets the box size.
	 * 
	 * @return the current box size.
	 */
	public int getBoxSize() {
		return boxSize;
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

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;

		// The size of the window adjusts the Labyrinth
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
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	/**
	 * Draws a box at a specific position.
	 * 
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

	/** Draws the shortest path from the finish point to the start point */
	public void drawPath() throws MainFunctionsException {
		if (maze == null) {
			JOptionPane.showMessageDialog(mainWindowFrame, "A maze must be initialized.", "Warning",
					JOptionPane.WARNING_MESSAGE);
			throw new MainFunctionsException("drawPath()", "A maze must be initialized");
		} else {
			if (maze.getDst().size() == 1 && maze.getSrc().size() == 1) {
				resetPath();
				shortestPath = Dijkstra.dijkstra(maze, maze.getSrc().get(0)).getShortestPathTo(maze.getDst().get(0));
				if (shortestPath.size() < 2) {
					JOptionPane.showMessageDialog(mainWindowFrame, "There is no path, sorry.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					pathFlag = true;
					timer.start();
				}
			} else {
				JOptionPane.showMessageDialog(mainWindowFrame,
						"Please check the number of the destination and the source point !", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/** Resets the path. */
	public void resetPath() {
		// Reset the pathFlag to false and currentIterationOfPath to 0.
		pathFlag = false;
		currentIterationOfPath = 0;
		shortestPath = null;
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		currentIterationOfPath++;
		repaint();
		if (currentIterationOfPath >= shortestPath.size() - 1) {
			timer.stop();
			arrived=true;
			repaint();	
			return;	
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (maze == null) {
			g.setColor(WindowModel.colorOfNotification); // Color of the Notification
			g.setFont(WindowModel.fontOfNotification); // Font
			drawString(g,
					"1. Open a file or click \"New\" to create a Labyrinth.\n2. Click \"Start\"to find the shortest path.\n3. Have fun!",
					margin, margin);
		} else {
			int numberOfHeight = maze.getLength(), numberOfWidth = maze.getWidth();
			for (int i = 0; i < numberOfHeight; i++) {
				for (int j = 0; j < numberOfWidth; j++) {
					// x is the horizontal axis, y is the vertical axis
					// i is vertically increasing, j is vertically increasing
					drawBox(g, maze.getBox(i, j), margin + j * boxSize, margin + i * boxSize);
				}
			}
		}
		if (pathFlag) {
			g.setColor(LabyrinthModel.colorOfPath);
			for (int i = 1; i < currentIterationOfPath; i++) {
				Box v = (Box) shortestPath.get(i);
				g.fillRect(margin + v.getY() * boxSize, margin + v.getX() * boxSize, boxSize, boxSize);
			}
			//Animation with icons moving
			BufferedImage img = null;
			if (boxSize >= LabyrinthModel.boxSize / 2) {
				try {
					Box b = (Box) shortestPath.get(currentIterationOfPath - 1);
					img = ImageIO.read(new File("Icons/Michael Jackson.png"));
					g.drawImage(img, margin + b.getY() * boxSize, margin + b.getX() * boxSize, boxSize, boxSize,
							mainWindowFrame);
					if(arrived){
						g.setColor(LabyrinthModel.colorOfPath);
						Box w = maze.getSrc().get(0);
						g.fillRect(margin + b.getY() * boxSize, margin + b.getX() * boxSize, boxSize, boxSize);		
						img = ImageIO.read(new File("Icons/Ooh.png"));
						g.drawImage(img, margin + w.getY() * boxSize, margin + w.getX() * boxSize, boxSize, boxSize,
								mainWindowFrame);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				finally{
					arrived=false;
				}
			}
		}
	}
	


					
		
}
