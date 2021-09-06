package com.david.giczi.webtris.model;

import java.util.List;

import com.david.giczi.webtris.model.shapes.AbstractShape;

public class GameState {

	
	private List<AbstractShape> actualShape;
	private List<AbstractShape> deletedComponents;
	private List<AbstractShape> shapeStore;
	private List<Boolean> logicBoard;
	private String shapeColor;
	private int score;
	
	
	public GameState() {
	}

	public List<AbstractShape> getActualShape() {
		return actualShape;
	}




	public void setActualShape(List<AbstractShape> actualShape) {
		this.actualShape = actualShape;
	}




	public List<AbstractShape> getDeletedComponents() {
		return deletedComponents;
	}




	public void setDeletedComponents(List<AbstractShape> deletedComponents) {
		this.deletedComponents = deletedComponents;
	}




	public List<Boolean> getLogicBoard() {
		return logicBoard;
	}


	public void setLogicBoard(List<Boolean> logicBoard) {
		this.logicBoard = logicBoard;
	}
	
	
	public List<AbstractShape> getShapeStore() {
		return shapeStore;
	}


	public void setShapeStore(List<AbstractShape> shapeStore) {
		this.shapeStore = shapeStore;
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
