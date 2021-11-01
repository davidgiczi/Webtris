package com.david.giczi.webtris.model;

import java.util.List;

public class DisplayerData {

	private ShapeData actualShape;
	private ShapeData nextShape;
	private List<Integer> displayedPositions;
	private List<ShapeData> shapeStore;
	private int score;
	private boolean isGameOver;
	

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

	public List<Integer> getDisplayedPositions() {
		return displayedPositions;
	}


	public void setDisplayedPositions(List<Integer> displayedPositions) {
		this.displayedPositions = displayedPositions;
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


	public boolean isGameOver() {
		return isGameOver;
	}


	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}


	@Override
	public String toString() {
		return "DisplayerData [actualShape=" + actualShape + ", nextShape=" + nextShape + ", displayedPositions="
				+ displayedPositions + ", shapeStore=" + shapeStore + ", score=" + score + ", isGameOver=" + isGameOver
				+ "]";
	}

	
}
