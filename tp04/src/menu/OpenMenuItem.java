package menu;

import exceptions.MazeReadingException;
import gui.MainWindowFrame;
import maze.Maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;



public class OpenMenuItem extends JMenuItem implements ActionListener {

	static final long serialVersionUID = 30122017L;
	
	/** The current Java frame. */
	private final MainWindowFrame mainWindowFrame;

	public OpenMenuItem(MainWindowFrame mainWindowFrame) {
		super("Open");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		this.mainWindowFrame = mainWindowFrame;
		addActionListener(this);
	}

	/** Action for the menu. */
	public final void actionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

		int result = chooser.showOpenDialog(mainWindowFrame);

		if (result == JFileChooser.APPROVE_OPTION) {
			//open the file with a labyrinth
			String path = chooser.getSelectedFile().getAbsolutePath();
			Maze newMaze = new Maze();
			try {
				newMaze.initFromTextFile(path);
			} catch (MazeReadingException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			mainWindowFrame.resetPath();
			if (mainWindowFrame.resizeBox(newMaze.getLength(), newMaze.getWidth())) {
				//Makes sure the labyrinth can be printed on screen clearly.
				mainWindowFrame.setMaze(newMaze);
			} else {
				JOptionPane.showMessageDialog(mainWindowFrame, "The width or/and the length is too large.", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}

}
