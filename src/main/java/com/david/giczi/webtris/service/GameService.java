package com.david.giczi.webtris.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.david.giczi.webtris.logic.TetrisLogic;
import com.david.giczi.webtris.model.DisplayerData;
import com.david.giczi.webtris.model.GameState;
import com.david.giczi.webtris.model.Player;
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

	public synchronized DisplayerData initGame(HttpServletRequest request, String playerId) {
		
		AbstractShape actualShape = ShapeFactory.getShape();
		AbstractShape nextShape = ShapeFactory.getShape();
		List<Boolean> logicBoard = initLogicBoard(actualShape);
		List<AbstractShape> shapeStore = initShapeStore();
		GameState gameState = new GameState();
		gameState.setActualShape(actualShape);
		gameState.setNextShape(nextShape);
		gameState.setLogicBoard(logicBoard);
		gameState.setShapeStore(shapeStore);
		gameState.setScore(0);
		gameState.setGameOver(false);
		request.getSession().setAttribute(playerId, gameState);
		DisplayerData displayerData = new DisplayerData();
		createActualShapeOfDisplayerDataFromActualShapeOfGameState(gameState, displayerData);
		createNextShapeOfDisplayerDataFromNextShapeOfGameState(gameState, displayerData);
		displayerData.setScore(0);
		
		return displayerData;
	}

	public synchronized DisplayerData goShapeLeft(HttpServletRequest request, String playerId) {

		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		List<ShapePosition> deletedPositions = new ArrayList<>();
		logic.setLogicBoard(gameState.getLogicBoard());
		
		if (logic.canShapeBeMovedToLeft(gameState.getActualShape())) {

			deletedPositions = gameState.getActualShape().moveToLeft();
			logic.addDeletedPositionToLogicBoard(deletedPositions);
			logic.addShapeToLogicBoard(gameState.getActualShape());
			gameState.setLogicBoard(logic.getLogicBoard());
			request.getSession().setAttribute(playerId, gameState);
		}

		return createDisplayerDataForShapeMoving(gameState);
	}

	public synchronized DisplayerData goShapeRight(HttpServletRequest request, String playerId) {

		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		List<ShapePosition> deletedPositions = new ArrayList<>();
		logic.setLogicBoard(gameState.getLogicBoard());

		if (logic.canShapeBeMovedToRight(gameState.getActualShape())) {

			deletedPositions = gameState.getActualShape().moveToRight();
			logic.addDeletedPositionToLogicBoard(deletedPositions);
			logic.addShapeToLogicBoard(gameState.getActualShape());
			gameState.setLogicBoard(logic.getLogicBoard());
			request.getSession().setAttribute(playerId, gameState);
		}

		return createDisplayerDataForShapeMoving(gameState);
	}

	public synchronized DisplayerData rotateShape(HttpServletRequest request, String playerId) {

		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		List<ShapePosition> deletedPositions = new ArrayList<>();
		logic.setLogicBoard(gameState.getLogicBoard());

		if (logic.canShapeBeRotated(gameState.getActualShape())) {

			deletedPositions = gameState.getActualShape().rotateShape();
			logic.addDeletedPositionToLogicBoard(deletedPositions);
			logic.addShapeToLogicBoard(gameState.getActualShape());
			gameState.setLogicBoard(logic.getLogicBoard());
			request.getSession().setAttribute(playerId, gameState);
		}

		return createDisplayerDataForShapeMoving(gameState);
	}

	public synchronized DisplayerData fallShapeDown(HttpServletRequest request, String playerId) {

		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		List<ShapePosition> deletedPositions = new ArrayList<>();
		logic.setLogicBoard(gameState.getLogicBoard());
		
		if (logic.canShapeBeMovedToDown(gameState.getActualShape())) {
			deletedPositions = fallDown(gameState, deletedPositions);
			request.getSession().setAttribute(playerId, gameState);
		}

		return createDisplayerDataForShapeMoving(gameState);
	}

	private DisplayerData createDisplayerDataForShapeMoving(GameState gameState) {
		DisplayerData displayerData = new DisplayerData();
		createActualShapeOfDisplayerDataFromActualShapeOfGameState(gameState, displayerData);
		addDisplayedPositionsToDisplayerData(displayerData);
		return displayerData;
	}

	private void addDisplayedPositionsToDisplayerData(DisplayerData displayerData) {
		
		List<Integer> displayedPositions = new ArrayList<>();
		
		for(int i = 0; i < logic.getLogicBoard().size(); i++) {
			if(logic.getLogicBoard().get(i)) {
				displayedPositions.add(i);
			}
		}
		displayerData.setDisplayedPositions(displayedPositions);
	}

	private List<ShapePosition> fallDown(GameState gameState, List<ShapePosition> deletedPositions) {

		deletedPositions = gameState.getActualShape().moveToDown();
		logic.addDeletedPositionToLogicBoard(deletedPositions);
		logic.addShapeToLogicBoard(gameState.getActualShape());

		return deletedPositions;
	}

	public synchronized DisplayerData playGame(HttpServletRequest request, String playerId) {

		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		List<ShapePosition> deletedPositions = new ArrayList<>();
		DisplayerData displayerData = new DisplayerData();
		logic.setLogicBoard(gameState.getLogicBoard());
		List<Integer> fullRowIndexStore = logic.getCompleteTrueRowsIndex();	
		
		if(gameState.isGameOver()) {
			return displayerData;
		}
		else if( !logic.canShapeBeMovedToDown(gameState.getActualShape()) && !fullRowIndexStore.isEmpty() ) {
			runFullRowProcess(gameState, fullRowIndexStore);
			getNextRound(gameState);
			createDisplayerDataInCaseOfFullRow(gameState, displayerData);
			createNextShapeOfDisplayerDataFromNextShapeOfGameState(gameState, displayerData);
		}
		else if ( !logic.canShapeBeMovedToDown(gameState.getActualShape()) && logic.isGameOver() ){
			calcScore(gameState);
			gameState.setGameOver(true);
			displayerData.setGameOver(true);
			Player player = playerService.getPlayerById(playerId);
			
			if(player.getScore() < logic.getScore()) {
			player.setScore(logic.getScore());
			long m = System.currentTimeMillis();
			player.setScoreDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli(m), ZoneId.systemDefault()));
			playerService.save(player);
	}
		}
		else if(logic.canShapeBeMovedToDown(gameState.getActualShape())) {
			
			deletedPositions = fallDown(gameState, deletedPositions);
		}
		else {
			
			 calcScore(gameState);
			 getNextRound(gameState);
			 createNextShapeOfDisplayerDataFromNextShapeOfGameState(gameState, displayerData);
		}
		
		request.getSession().setAttribute(playerId, gameState);
		displayerData.setScore(gameState.getScore());
		addDisplayedPositionsToDisplayerData(displayerData);
		createActualShapeOfDisplayerDataFromActualShapeOfGameState(gameState, displayerData);
		
		return displayerData;
	}

	
	private void getNextRound(GameState gameState) {

		AbstractShape actualShape = setActualShapeToRandomPlaceForDisplayer(gameState.getNextShape());
		AbstractShape nextShape = ShapeFactory.getShape();
		gameState.setActualShape(actualShape);
		gameState.setNextShape(nextShape);
		logic.addShapeToLogicBoard(actualShape);
		gameState.setLogicBoard(logic.getLogicBoard());
		logic.setShapeStore(gameState.getShapeStore());
		logic.addShapeToStore(gameState.getActualShape());
		gameState.setShapeStore(logic.getShapeStore());	
	}
	
	private void calcScore(GameState gameState) {
		logic.setScore(gameState.getScore());
		logic.calcScore(gameState.getActualShape());
		gameState.setScore(logic.getScore());
	}

	private void runFullRowProcess(GameState gameState, List<Integer> fullRowIndexStore) {
		
		int score = gameState.getScore() + 100 *  fullRowIndexStore.size();
		gameState.setScore(score);
		logic.setShapeStore(gameState.getShapeStore());
		logic.deleteCompleteTrueRowsFromShapeComponent(fullRowIndexStore);
		logic.increaseRowNumberForShapeComponentInShapeStore(fullRowIndexStore);
		logic.refreshLogicBoard();
		
	}
	
	private List<Boolean> initLogicBoard(AbstractShape actualShape) {
		logic.initLogicBoard();
		actualShape = setActualShapeToRandomPlaceForDisplayer(actualShape);
		logic.addShapeToLogicBoard(actualShape);
		return logic.getLogicBoard();
	}
	
	public AbstractShape setActualShapeToRandomPlaceForDisplayer(AbstractShape actualShape) {
		
		int randDeltaX = (int) (Math.random() * 7);
		for(int i = 0;  i < actualShape.shapeComponent.size(); i++) {
			
			int displayerPositionX = actualShape.shapeComponent.get(i).getDisplayer_x() + randDeltaX;
			int displayerPositionY = actualShape.shapeComponent.get(i).getDisplayer_y();
			
			actualShape.shapeComponent.set(i, new ShapePosition(displayerPositionX, displayerPositionY));
		}
		
		return actualShape;
	}

	private List<AbstractShape> initShapeStore() {
		logic.setShapeStore(null);
		logic.addShapeToStore(null);
		return logic.getShapeStore();
	}

	private void createActualShapeOfDisplayerDataFromActualShapeOfGameState(GameState gameState,
			DisplayerData displayerData) {

		List<Integer> actualShapePositions = gameState.getActualShape().shapeComponent.stream()
				.map(c -> c.getLogicBoardIndex()).collect(Collectors.toList());
		ShapeData actualShape = new ShapeData();
		actualShape.setShapePositions(actualShapePositions);
		actualShape.setShapeColor(gameState.getActualShape().shapeColor);
		displayerData.setActualShape(actualShape);
		
	}

	private void createNextShapeOfDisplayerDataFromNextShapeOfGameState(GameState gameState,
			DisplayerData displayerData) {

		List<Integer> nextShapePositions = gameState.getNextShape().shapeComponent.stream()
				.map(c -> c.getLogicBoardIndex()).collect(Collectors.toList());
		ShapeData nextShape = new ShapeData();
		nextShape.setShapePositions(nextShapePositions);
		nextShape.setShapeColor(gameState.getNextShape().shapeColor);
		displayerData.setNextShape(nextShape);
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
	
	public void saveActualScore(HttpServletRequest request, String playerId) {
		
		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		Player player = playerService.getPlayerById(playerId);
		
		if(gameState.getScore() > player.getScore()) {
			player.setScore(gameState.getScore());
			long m = System.currentTimeMillis();
			player.setScoreDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli(m), ZoneId.systemDefault()));
			playerService.save(player);
		}
	}
		
}
