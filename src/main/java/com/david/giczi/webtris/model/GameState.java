package com.david.giczi.webtris.model;

import java.util.List;

import com.david.giczi.webtris.model.shapes.AbstractShape;

public class GameState {

	
	private AbstractShape actualShape;
	private AbstractShape nextShape;
	private List<AbstractShape> shapeStore;
	private List<Boolean> logicBoard;
	private int score;
	
	
	public GameState() {
	}


	public AbstractShape getActualShape() {
		return actualShape;
	}


	public void setActualShape(AbstractShape actualShape) {
		this.actualShape = actualShape;
	}


	public AbstractShape getNextShape() {
		return nextShape;
	}


	public void setNextShape(AbstractShape nextShape) {
		this.nextShape = nextShape;
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

	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	
}
