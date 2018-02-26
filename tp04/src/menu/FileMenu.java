package menu;

import javax.swing.JMenu;
import gui.MainWindowFrame;

/** The File menu inside the menu bar. */

public final class FileMenu extends JMenu
{
	static final long serialVersionUID = 30122017L;

	public FileMenu(MainWindowFrame mainWindowFrame)
	{
		super("File") ;
		add(new NewMenuItem(mainWindowFrame));
		add(new OpenMenuItem(mainWindowFrame));
		addSeparator();
		add(new SaveMenuItem(mainWindowFrame));
		addSeparator();
		add(new QuitMenuItem(mainWindowFrame)) ;

	}
}
