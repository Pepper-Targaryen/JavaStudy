package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import exceptions.MainFunctionsException;
import menu.ResetButton;
import menu.StartButton;

/**The class of the button panel*/

public class ButtonsPanel extends JPanel implements ActionListener {
	
	static final long serialVersionUID = 30122017L;
	
	private final StartButton start;
	private final ResetButton reset;
	private MainWindowFrame mainWindowFrame;
	
	/**Constructor
	 * @param mainWindowFrame - the window frame
	 */
	public ButtonsPanel(MainWindowFrame mainWindowFrame) {
		this.mainWindowFrame = mainWindowFrame;
		setLayout(new GridLayout(1, 2)); // 1 row, 2 columns

		// Start Button
		start = new StartButton(mainWindowFrame);
		start.setBackground(Color.CYAN);
		start.setForeground(Color.MAGENTA);
		add(start);

		// Restart Button
		reset = new ResetButton(mainWindowFrame);
		reset.setBackground(Color.CYAN);
		reset.setForeground(Color.MAGENTA);
		add(reset);

		// Inscription of Action Listener
		start.addActionListener(this);
		reset.addActionListener(this);
		start.setActionCommand("start");
		reset.setActionCommand("reset");

	}
	
	/**This method triggers the solving of the maze.
	 * @param e - the action of clicking the buttons*/
	@Override
	public void actionPerformed(ActionEvent e){
		try{
			if (e.getActionCommand().equals("start")) {
				mainWindowFrame.drawPath();
			} else if (e.getActionCommand().equals("reset")) {
				mainWindowFrame.resetPath();
			}

		}
		catch(MainFunctionsException z){
			System.out.println("MainFunctionsException : "+ z.getMessage());
		}
	}
}