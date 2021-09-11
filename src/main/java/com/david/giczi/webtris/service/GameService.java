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

	public DisplayerData initGame(HttpServletRequest request, String playerId) {

		AbstractShape actualShape = ShapeFactory.getShape();
		AbstractShape nextShape = ShapeFactory.getShape();
		List<Boolean> logicBoard = initLogicBoard(actualShape);
		List<AbstractShape> shapeStore = initShapeStore(actualShape);
		GameState gameState = new GameState();
		gameState.setActualShape(actualShape);
		gameState.setNextShape(nextShape);
		gameState.setLogicBoard(logicBoard);
		gameState.setShapeStore(shapeStore);
		gameState.setScore(0);
		request.getSession().setAttribute(playerId, gameState);
		return createDisplayerData(gameState, null, false);
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

	private DisplayerData createDisplayerData(GameState gameState, List<ShapePosition> delPositions,
			boolean isFullRow) {

		List<Integer> actualShapePositions = gameState.getActualShape().shapeComponent.stream()
				.map(c -> c.getLogicBoardIndex()).collect(Collectors.toList());
		ShapeData actualShape = new ShapeData();
		actualShape.setShapePositions(actualShapePositions);
		actualShape.setShapeColor(gameState.getActualShape().shapeColor);
		ShapeData nextShape = new ShapeData();
		List<Integer> nextShapePositions = gameState.getNextShape().shapeComponent.stream()
				.map(c -> c.getLogicBoardIndex()).collect(Collectors.toList());
		nextShape.setShapePositions(nextShapePositions);
		nextShape.setShapeColor(gameState.getNextShape().shapeColor);
		List<Integer> deletedPositions;
		if (delPositions != null) {
			deletedPositions = delPositions.stream().map(p -> p.getLogicBoardIndex()).collect(Collectors.toList());
		} else {
			deletedPositions = new ArrayList<>();
		}
		DisplayerData displayerData = new DisplayerData();
		displayerData.setActualShape(actualShape);
		displayerData.setNextShape(nextShape);
		displayerData.setDeletedPositions(deletedPositions);
		displayerData.setScore(gameState.getScore());
		if (isFullRow) {
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
		return displayerData;
	}

}
