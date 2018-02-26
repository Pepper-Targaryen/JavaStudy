package menu;

import gui.MainWindowFrame;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
/** The Quit menu item inside the File menu. */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public final class QuitMenuItem extends JMenuItem
	implements ActionListener
	
{
	static final long serialVersionUID = 30122017L;
	
	/** The current Java frame. */
	private final MainWindowFrame mainWindowFrame ;
	
	public QuitMenuItem(MainWindowFrame mainWindowFrame)
	{
		super("Quit");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		this.mainWindowFrame = mainWindowFrame;
		addActionListener(this);
	}
	
	/** Action for the menu. */
	public final void actionPerformed(ActionEvent evt)
	{
		mainWindowFrame.quit() ;
	}
	
}
