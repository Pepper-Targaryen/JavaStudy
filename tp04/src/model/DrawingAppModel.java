package model;

import java.awt.*;
import java.util.*;

public class DrawingAppModel {
	private final ArrayList<Segment> editedSegments = new ArrayList<Segment>(
			128);
	private Color currentColor = new Color(0, 0, 0);
	private Segment currentSegment = null;
	private Segment selectedSegment = null;
	private boolean modified = false;

	// getters et setters
	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public Segment getCurrentSegment() {
		return currentSegment;
	}

	public void setCurrentSegment(Segment currentSegment) {
		this.currentSegment = currentSegment;
	}

	public Segment getSelectedSegment() {
		return selectedSegment;
	}

	public void setSelectedSegment(Segment selectedSegment) {
		this.selectedSegment = selectedSegment;
	}

	public ArrayList<Segment> getEditedSegments() {
		return editedSegments;
	}

}