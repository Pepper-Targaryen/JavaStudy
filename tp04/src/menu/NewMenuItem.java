package menu;

import gui.MainWindowFrame;
import maze.Maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class NewMenuItem extends JMenuItem implements ActionListener {

	static final long serialVersionUID = 30122017L;
	
	/** The current Java frame. */
	private final MainWindowFrame mainWindowFrame;

	public NewMenuItem(MainWindowFrame mainWindowFrame) {
		super("New", KeyEvent.VK_N);
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		this.mainWindowFrame = mainWindowFrame;
		addActionListener(this);
	}

	/** Action for the menu. */
	public final void actionPerformed(ActionEvent evt) {

		String heightString = (String) JOptionPane.showInputDialog(mainWindowFrame, "Input the height", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, null, 10);

		mainWindowFrame.resetPath();
		if(processInput(heightString)==0){
			return;
		}	
		String widthString = (String) JOptionPane.showInputDialog(mainWindowFrame, "Input the width", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, null, 10);
		if(processInput(widthString)==0){
			return;
		}
		int height=processInput(heightString);
		int width=processInput(widthString);
		if (mainWindowFrame.resizeBox(height, width)) {
			//Make sure the labyrinth can be printed on screen clearly.
			mainWindowFrame.setMaze(new Maze(height, width));
		} else {
			JOptionPane.showMessageDialog(mainWindowFrame, "The width or/and the length is too large.", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**process the input value
	 * @param s the string(dimension of the maze)
	 */
	public int processInput(String s){
		int dimension = 0;
		if (s==null){
			return 0;
		}
		try {
			dimension = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(mainWindowFrame, "The input is not a number", "Error",
					JOptionPane.WARNING_MESSAGE);
			return 0;
		} 
		if (dimension==0){
			JOptionPane.showMessageDialog(mainWindowFrame, "The input must be strictly positive", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		return dimension;

	}

}
