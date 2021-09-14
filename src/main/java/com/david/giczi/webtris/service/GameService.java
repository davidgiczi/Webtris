package com.david.giczi.webtris.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.david.giczi.webtris.logic.TetrisLogic;
import com.david.giczi.webtris.model.DisplayerData;
import com.david.giczi.webtris.model.GameState;
import com.david.giczi.webtris.model.ShapeData;
import com.david.giczi.webtris.model.ShapeFactory;
import com.david.giczi.webtris.model.ShapePosition;
import com.david.giczi.webtris.model.shapes.AbstractShape;

@Service
public class GameService {

	@Autowired
	private TetrisLogic logic;
	@Autowired
	private PlayerService playerService;

	public DisplayerData initGame(HttpServletRequest request, String playerId) {

		AbstractShape actualShape = ShapeFactory.getShape(true);
		AbstractShape nextShape = ShapeFactory.getShape(false);
		List<Boolean> logicBoard = initLogicBoard(actualShape);
		List<AbstractShape> shapeStore = initShapeStore(actualShape);
		GameState gameState = new GameState();
		gameState.setActualShape(actualShape);
		gameState.setNextShape(nextShape);
		gameState.setLogicBoard(logicBoard);
		gameState.setShapeStore(shapeStore);
		gameState.setScore(playerService.getScoreById(playerId));
		request.getSession().setAttribute(playerId, gameState);
		DisplayerData displayerData = new DisplayerData();
		createActualShapeOfDisplayerDataFromActualShapeOfGameState(gameState, displayerData);
		createNextShapeOfDisplayerDataFromNextShapeOfGameState(gameState, displayerData);
		displayerData.setScore(gameState.getScore());
		return displayerData;
	}

	private List<Boolean> initLogicBoard(AbstractShape actualShape) {
		logic.initLogicBoard();
		logic.addShapeToLogicBoard(actualShape);
		return logic.getLogicBoard();
	}

	private List<AbstractShape> initShapeStore(AbstractShape actualShape) {
		logic.addShapeToStore(actualShape);
		return logic.getShapeStore();
	}

	private void createActualShapeOfDisplayerDataFromActualShapeOfGameState(GameState gameState, DisplayerData displayerData) {
		
		List<Integer> actualShapePositions = gameState.getActualShape().shapeComponent.stream()
				.map(c -> c.getLogicBoardIndex()).collect(Collectors.toList());
		ShapeData actualShape = new ShapeData();
		actualShape.setShapePositions(actualShapePositions);
		actualShape.setShapeColor(gameState.getActualShape().shapeColor);
		displayerData.setActualShape(actualShape);
	}
	
	private void createNextShapeOfDisplayerDataFromNextShapeOfGameState(GameState gameState, DisplayerData displayerData) {
		
		List<Integer> nextShapePositions = gameState.getNextShape().shapeComponent.stream()
				.map(c -> c.getLogicBoardIndex()).collect(Collectors.toList());
		ShapeData nextShape = new ShapeData();
		nextShape.setShapePositions(nextShapePositions);
		nextShape.setShapeColor(gameState.getNextShape().shapeColor);
		displayerData.setNextShape(nextShape);
	}
	
	private void createDeletedPositionsOfDisplayerDataFromDeletedPositionsOfGameState
	(List<ShapePosition> deletedPositions, DisplayerData displayerData) {
		
		List<Integer> deletedPositionsOfDisplayerData = deletedPositions
				.stream().map(p -> p.getLogicBoardIndex()).collect(Collectors.toList());
		displayerData.setDeletedPositions(deletedPositionsOfDisplayerData);
	}
	
	private void createDisplayerDataInCaseOfFullRow(GameState gameState, DisplayerData displayerData) {
		
			List<ShapeData> shapeStore = new ArrayList<>();
			gameState.getShapeStore().forEach(s -> {

				List<Integer> shapePositions = s.shapeComponent.stream().map(c -> c.getLogicBoardIndex())
						.collect(Collectors.toList());
				ShapeData shapeData = new ShapeData();
				shapeData.setShapePositions(shapePositions);
				shapeData.setShapeColor(s.shapeColor);
				shapeStore.add(shapeData);
			});
			
			displayerData.setShapeStore(shapeStore);
	}

	public DisplayerData goShapeLeft(HttpServletRequest request, String playerId) {
		
		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		List<ShapePosition> deletedPositions;
		
		if(logic.canShapeBeMovedToLeft(gameState.getActualShape())) {
			
			deletedPositions = gameState.getActualShape().moveToLeft();
			logic.setLogicBoard(gameState.getLogicBoard());
			logic.addDeletedPositionToLogicBoard(deletedPositions);
			logic.addShapeToLogicBoard(gameState.getActualShape());
			gameState.setLogicBoard(logic.getLogicBoard());
			request.getSession().setAttribute(playerId, gameState);
		}
		else {
			deletedPositions = new ArrayList<>();
		}
		
		DisplayerData displayerData = new DisplayerData();
		
		createActualShapeOfDisplayerDataFromActualShapeOfGameState(gameState, displayerData);
		createDeletedPositionsOfDisplayerDataFromDeletedPositionsOfGameState(deletedPositions, displayerData);
		
		return displayerData;
	}
	
public DisplayerData goShapeRight(HttpServletRequest request, String playerId) {
		
		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		List<ShapePosition> deletedPositions;
		
		if(logic.canShapeBeMovedToRight(gameState.getActualShape())) {
			
			deletedPositions = gameState.getActualShape().moveToRight();
			logic.setLogicBoard(gameState.getLogicBoard());
			logic.addDeletedPositionToLogicBoard(deletedPositions);
			logic.addShapeToLogicBoard(gameState.getActualShape());
			gameState.setLogicBoard(logic.getLogicBoard());
			request.getSession().setAttribute(playerId, gameState);
		}
		else {
			deletedPositions = new ArrayList<>();
		}
		
		DisplayerData displayerData = new DisplayerData();
		
		createActualShapeOfDisplayerDataFromActualShapeOfGameState(gameState, displayerData);
		createDeletedPositionsOfDisplayerDataFromDeletedPositionsOfGameState(deletedPositions, displayerData);
		
		return displayerData;
	}

	
	
}
