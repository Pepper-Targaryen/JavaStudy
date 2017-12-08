package ui;

import javax.swing.* ;

public class EraseSegmentButton extends JButton
{
	private final DrawingApp drawingApp ;
	
	public EraseSegmentButton(DrawingApp drawingApp)
	{
		super("Erase segment") ; // Button's text
		
		this.drawingApp = drawingApp ;		
	}
}