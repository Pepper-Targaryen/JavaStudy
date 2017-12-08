package ui;

import java.awt.*;

import javax.swing.* ;

public class ColorIndicator extends JPanel
{
	private final DrawingApp drawingApp ;
	
	public ColorIndicator(DrawingApp drawingApp)
	{
			this.drawingApp = drawingApp ;
	}
	@Override
	protected final void paintComponent(Graphics g)
	{
	   // Paint the background
	   super.paintComponent(g) ;
			
	   // Get widget dimension
	   int w = getWidth() ;
	   int h = getHeight() ;
			
	   // Draw a rectangle
	   g.setColor(drawingApp.getDrawingAppModel().getCurrentColor()) ;
	   g.fillRoundRect(4, 4, w-8, h-8, 15, 15) ;

	   // Draw a border
	   g.setColor(Color.BLACK); 
	   g.drawRoundRect(4, 4, w-8, h-8, 15, 15) ;
	}
}