package ui;

import java.awt.*;
import javax.swing.* ;

public class ButtonsPanel extends JPanel
{
	private final ColorIndicator     colorIndicator ;
	private final ColorChooserButton colorChooser ;
	private final EraseSegmentButton eraseSegment ;
	
	public ButtonsPanel(DrawingApp drawingApp)
	{
		setLayout(new GridLayout(1,3)) ; // 1 row, 3 columns
		
		add(colorIndicator = new ColorIndicator     (drawingApp)) ;
		add(colorChooser   = new ColorChooserButton (drawingApp)) ;
		add(eraseSegment   = new EraseSegmentButton (drawingApp)) ;
	}
}