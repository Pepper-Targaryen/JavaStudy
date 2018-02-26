package menu;

import javax.swing.JMenuBar;
import gui.MainWindowFrame;

/** The Menu Bar of the game frame. */

public final class MainMenuBar extends JMenuBar
{
	static final long serialVersionUID = 1L ;
	
	public MainMenuBar(MainWindowFrame mainWindowFrame)
	{
		super() ;	
		add(new FileMenu(mainWindowFrame)) ;
	}

}
