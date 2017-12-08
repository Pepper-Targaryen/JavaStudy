package ui;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
	private final DrawingApp drawingApp;

	public DrawingPanel(DrawingApp drawingApp) {
		this.drawingApp = drawingApp;

		setBackground(Color.WHITE);

		// for pack() instruction
		setPreferredSize(new Dimension(256, 256));
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Paint the background
		super.paintComponent(g);

		// Ask the model to draw the segments
		drawingApp.getDrawingAppModel().paintSegments(g);
	}
}