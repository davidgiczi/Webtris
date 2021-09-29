package com.david.giczi.webtris.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.david.giczi.webtris.model.DisplayerData;
import com.david.giczi.webtris.service.GameService;

@RestController
@RequestMapping("/webtris")
public class WebtrisOperations {
	
	@Autowired
	private GameService gameService;
	

	@GetMapping("/start")
	public ResponseEntity<DisplayerData> init(@CookieValue(value = "playerId") String playerId, HttpServletRequest request) {
	
		DisplayerData data = gameService.initGame(request, playerId);
		
		return new ResponseEntity<DisplayerData>(data, HttpStatus.OK);
	}
	
	@GetMapping("/left")
	public ResponseEntity<DisplayerData> goLeft(@CookieValue(value = "playerId") String playerId, HttpServletRequest request){
		
		DisplayerData data = gameService.goShapeLeft(request, playerId);
		
		return new ResponseEntity<DisplayerData>(data, HttpStatus.OK);
	}
	
	@GetMapping("/right")
	public ResponseEntity<DisplayerData> goRight(@CookieValue(value = "playerId") String playerId, HttpServletRequest request){
		
		DisplayerData data = gameService.goShapeRight(request, playerId);
		
		return new ResponseEntity<DisplayerData>(data, HttpStatus.OK);
	}
	
	@GetMapping("/rotate")
	public ResponseEntity<DisplayerData> rotate(@CookieValue(value = "playerId") String playerId, HttpServletRequest request){
		
		DisplayerData data = gameService.rotateShape(request, playerId);
		
		return new ResponseEntity<DisplayerData>(data, HttpStatus.OK);
	}
	
	@GetMapping("/fallDown")
	public ResponseEntity<DisplayerData> fallDown(@CookieValue(value = "playerId") String playerId, HttpServletRequest request){
		
		DisplayerData data = gameService.fallShapeDown(request, playerId);
		
		return new ResponseEntity<DisplayerData>(data, HttpStatus.OK);
	}
	
	@GetMapping("/step")
	public ResponseEntity<DisplayerData> playGame(@CookieValue(value = "playerId") String playerId, HttpServletRequest request){
		
		
		DisplayerData data = gameService.playGame(request, playerId);
		
		return new ResponseEntity<DisplayerData>(data, HttpStatus.OK);
	}
	
	@GetMapping("/save")
	public ResponseEntity<Object> saveActualScore(@CookieValue(value = "playerId") String playerId, HttpServletRequest request){
		
		gameService.saveActualScore(request, playerId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
