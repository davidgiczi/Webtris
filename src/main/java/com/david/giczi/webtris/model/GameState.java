package com.david.giczi.webtris.model;

import java.util.List;

public class GameState {

	
	private List<Integer> actualShape;
	private List<Integer> deletedComponents;
	private String shapeColor;
	private int score;
	
	
	public GameState() {
	}


	public List<Integer> getActualShape() {
		return actualShape;
	}


	public void setActualShape(List<Integer> actualShape) {
		this.actualShape = actualShape;
	}


	public List<Integer> getDeletedComponents() {
		return deletedComponents;
	}


	public void setDeletedComponents(List<Integer> deletedComponents) {
		this.deletedComponents = deletedComponents;
	}


	public String getShapeColor() {
		return shapeColor;
	}


	public void setShapeColor(String shapeColor) {
		this.shapeColor = shapeColor;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	
}
