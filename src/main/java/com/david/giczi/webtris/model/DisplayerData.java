package com.david.giczi.webtris.model;

import java.util.List;

public class DisplayerData {

	private List<Integer> actualShapesPositions;
	private List<Integer> nextShapePositions;
	private List<Integer> deletedPositions;
	private List<String> shapeColors;
	private int score;

	public DisplayerData() {
	}

	public List<Integer> getActualShapesPositions() {
		return actualShapesPositions;
	}

	public void setActualShapePositions(List<Integer> actualShapesPositions) {
		this.actualShapesPositions = actualShapesPositions;
	}

	public List<Integer> getNextShapePositions() {
		return nextShapePositions;
	}

	public void setNextShapePositions(List<Integer> nextShapePositions) {
		this.nextShapePositions = nextShapePositions;
	}

	public List<String> getShapeColors() {
		return shapeColors;
	}

	public void setShapeColors(List<String> shapeColors) {
		this.shapeColors = shapeColors;
	}

	public List<Integer> getDeletedPositions() {
		return deletedPositions;
	}

	public void setDeletedPositions(List<Integer> deletedPositions) {
		this.deletedPositions = deletedPositions;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
