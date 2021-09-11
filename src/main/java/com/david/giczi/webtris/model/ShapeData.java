package com.david.giczi.webtris.model;

import java.util.List;

public class ShapeData {
	
	private List<Integer> shapePositions;
	private String shapeColor;

	
	public ShapeData() {
	}


	public List<Integer> getShapePositions() {
		return shapePositions;
	}


	public void setShapePositions(List<Integer> shapePositions) {
		this.shapePositions = shapePositions;
	}


	public String getShapeColor() {
		return shapeColor;
	}


	public void setShapeColor(String shapeColor) {
		this.shapeColor = shapeColor;
	}
	
	
	
}
