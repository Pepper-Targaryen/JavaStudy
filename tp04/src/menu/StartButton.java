package menu;

import gui.MainWindowFrame;

import javax.swing.JButton;

public class StartButton extends JButton
{
	static final long serialVersionUID = 30122017L;
	
	@SuppressWarnings("unused")
	private final MainWindowFrame mainWindowFrame ;
	
	public StartButton(MainWindowFrame mainWindowFrame)
	{
		super("Start") ; // Button's text
		
		this.mainWindowFrame = mainWindowFrame ;
	}	
}