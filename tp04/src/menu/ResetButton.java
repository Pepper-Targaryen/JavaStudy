package menu;

import gui.MainWindowFrame;

import javax.swing.JButton;

public class ResetButton extends JButton
{
	static final long serialVersionUID = 30122017L;
	
	@SuppressWarnings("unused")
	private final MainWindowFrame mainWindowFrame ;
	
	public ResetButton(MainWindowFrame mainWindowFrame)
	{
		super("Reset") ; // Button's text
		
		this.mainWindowFrame = mainWindowFrame ;
	}
}