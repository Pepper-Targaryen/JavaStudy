package ui;

import javax.swing.* ;

public class ColorChooserButton extends JButton
{
	private final DrawingApp drawingApp ;
	
	public ColorChooserButton(DrawingApp drawingApp)
	{
		super("Choose color") ; // Button's text
		
		this.drawingApp = drawingApp ;
	}
}