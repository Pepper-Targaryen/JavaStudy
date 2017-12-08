package ui;

import javax.swing.* ;
import java.awt.*;

public class WindowPanel extends JPanel
{
    private final DrawingPanel drawingPanel ;
    private final ButtonsPanel buttonsPanel ;
    
	public WindowPanel(DrawingApp drawingApp)
	{
		setLayout(new BorderLayout()) ;
		
		add(drawingPanel = new DrawingPanel (drawingApp), BorderLayout.CENTER) ;
		add(buttonsPanel = new ButtonsPanel (drawingApp), BorderLayout.SOUTH) ;
	}
	
}