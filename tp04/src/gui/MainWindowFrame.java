package gui;

import java.awt.*;

import javax.swing.*;

import constant.LabyrinthModel;
import exceptions.MainFunctionsException;
import maze.Maze;
import menu.MainMenuBar;

/** The window */

public class MainWindowFrame extends JFrame {
	
	static final long serialVersionUID = 30122017L;
	
	private ButtonsPanel buttonsPanel;
	private GamePanel gamePanel;
	private GameMirrorPanel gameMirrorPanel;
	private JPanel demonstration = new JPanel(new BorderLayout());
	private HelpPanel help = new HelpPanel();
	private JTabbedPane jTabbedPane = new JTabbedPane();

	// Dimension of the screen
	public static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height,
			screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	public MainWindowFrame() {

		// Creates Widgets
		buttonsPanel = new ButtonsPanel(this);
		gamePanel = new GamePanel(this);
		gameMirrorPanel = new GameMirrorPanel(this);

		// Adds Widgets
		setJMenuBar(new MainMenuBar(this));

		demonstration.add(gamePanel, BorderLayout.CENTER);
		demonstration.add(buttonsPanel, BorderLayout.SOUTH);
		jTabbedPane.add("Demonstration", demonstration);
		jTabbedPane.add("Edit Mode", gameMirrorPanel);
		jTabbedPane.add("Help", help);
		add(jTabbedPane, BorderLayout.CENTER);

		// Sets Window
		setTitle("JavaProject: Dijkstra's Algorithm Applied in an Labyrinth");

		setSize(screenWidth / 2, screenHeight / 2); // maybe change later
		setLocation(screenWidth / 4, screenHeight / 4);

		setBackground(new Color(204, 204, 204));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Sets the maze of the demonstration and edit panels.
	 * 
	 * @param m
	 *             maze
	 */
	public void setMaze(Maze m) {
		gamePanel.setMaze(m);
		gameMirrorPanel.setMaze(m);
		pack();
	}

	/**
	 * Gets the maze of the demonstration panel.
	 * 
	 * @return the maze of the panel
	 */
	public Maze getMaze() {
		return gamePanel.getMaze();
	}

	/**
	 * Displays the path on the screen.
	 * 
	 * @throws MainFunctionsException
	 */
	public void drawPath() throws MainFunctionsException {
		gamePanel.drawPath();
	}

	/** Remove the path */
	public void resetPath() {
		gamePanel.resetPath();
	}

	/** Quit the application. */
	public final void quit() {
		int response = JOptionPane.showConfirmDialog(this, "Really quit ?", "Quit application",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		switch (response) {
		case JOptionPane.OK_OPTION:
			System.exit(0);
		case JOptionPane.NO_OPTION:
			break;
		}
	}

	/**
	 * Get the game panel.
	 * 
	 * @return the game panel
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * Resizes the box in the frame if the dimensions are too high.
	 * 
	 * @param height
	 *            the height of the maze
	 * @param width
	 *            the width of the maze
	 *            
	 * @return true if it can be resized, false otherwise
	 */
	public boolean resizeBox(int height, int width) {
		gamePanel.resizeBox(height, width, screenHeight, screenWidth);
		gameMirrorPanel.resizeBox(height, width, screenHeight, screenWidth);
		if (gamePanel.getBoxSize() < LabyrinthModel.minBoxSize) {
			//The BoxSize must at least be bigger than 1
			gamePanel.resetBoxSize();
			gameMirrorPanel.resetBoxSize();
			return false;
		} else
			return true;
	}

}
