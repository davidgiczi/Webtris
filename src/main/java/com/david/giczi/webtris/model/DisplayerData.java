package com.david.giczi.webtris.model;

import java.util.List;

public class DisplayerData {

	private ShapeData actualShape;
	private ShapeData nextShape;
	private List<Integer> deletedPositions;
	private List<ShapeData> shapeStore;
	private int score;
	private boolean isTheEnd;
	private boolean isStandShape;

	public DisplayerData() {
	}


	public ShapeData getActualShape() {
		return actualShape;
	}

	public void setActualShape(ShapeData actualShape) {
		this.actualShape = actualShape;
	}

	public ShapeData getNextShape() {
		return nextShape;
	}

	public void setNextShape(ShapeData nextShape) {
		this.nextShape = nextShape;
	}


	public List<Integer> getDeletedPositions() {
		return deletedPositions;
	}


	public void setDeletedPositions(List<Integer> deletedPositions) {
		this.deletedPositions = deletedPositions;
	}


	public List<ShapeData> getShapeStore() {
		return shapeStore;
	}


	public void setShapeStore(List<ShapeData> shapeStore) {
		this.shapeStore = shapeStore;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public boolean isTheEnd() {
		return isTheEnd;
	}


	public void setTheEnd(boolean isTheEnd) {
		this.isTheEnd = isTheEnd;
	}


	public boolean isStandShape() {
		return isStandShape;
	}


	public void setStandShape(boolean isStandShape) {
		this.isStandShape = isStandShape;
	}
	
}
