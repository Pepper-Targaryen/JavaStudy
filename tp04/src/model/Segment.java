package model;

import java.awt.*;
import java.awt.geom.*;

public class Segment extends Line2D.Float {
	private Color color;

	public Segment(float x1, float y1, float x2, float y2, Color color) {
		super(x1, y1, x2, y2);

		this.color = color;
	}

}