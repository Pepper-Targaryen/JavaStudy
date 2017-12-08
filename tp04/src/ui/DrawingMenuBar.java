package ui;

import javax.swing.*;

public class DrawingMenuBar extends JMenuBar {

	private final FileMenu fileMenu;

	public DrawingMenuBar(DrawingApp drawingApp) {
		super();

		// Create and add menus
		add(fileMenu = new FileMenu(drawingApp));
	}
}