package menu;

import gui.MainWindowFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class SaveMenuItem extends JMenuItem implements ActionListener {
	
	static final long serialVersionUID = 30122017L;
	
	/** The current Java frame. */
	private final MainWindowFrame mainWindowFrame;

	public SaveMenuItem(MainWindowFrame mainWindowFrame) {
		super("Save", KeyEvent.VK_S);
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		this.mainWindowFrame = mainWindowFrame;
		addActionListener(this);
	}

	/** Action for the menu. */
	public final void actionPerformed(ActionEvent evt) {
		if (mainWindowFrame.getMaze() != null) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int result = chooser.showSaveDialog(mainWindowFrame);

			if (result == JFileChooser.APPROVE_OPTION) {
				// Choose where to store the file of the customized labyrinth
				String path = chooser.getSelectedFile().getAbsolutePath();
				mainWindowFrame.getMaze().saveToTextFile(path);
			}
		} else {
			JOptionPane.showMessageDialog(mainWindowFrame, "Please created your customized labyrinth first.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

	}
}
