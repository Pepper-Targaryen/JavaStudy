package ui;

import javax.swing.*;
import model.DrawingAppModel;

public class DrawingApp extends JFrame {
	private final DrawingMenuBar drawingMenuBar;
	private final WindowPanel windowPanel;
	private DrawingAppModel drawingAppModel = new DrawingAppModel();

	public DrawingAppModel getDrawingAppModel() {
		return drawingAppModel;
	}

	public void setDrawingAppModel(DrawingAppModel drawingAppModel) {
		this.drawingAppModel = drawingAppModel;
	}

	public DrawingApp() {
		super("Drawing Application"); // Windows Title
		setJMenuBar(drawingMenuBar = new DrawingMenuBar(this));
		setContentPane(windowPanel = new WindowPanel(this));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Explicit !
		pack(); // Components sizes and positions
		setVisible(true); // The great show
	}

}
