package com.david.giczi.webtris.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.david.giczi.webtris.model.DisplayerData;
import com.david.giczi.webtris.model.GameState;
import com.david.giczi.webtris.service.GameService;

@RestController
@RequestMapping("/webtris")
public class WebtrisRestOperations {
	
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
		
		GameState gameState = (GameState) request.getSession().getAttribute(playerId);
		DisplayerData displayerData = gameService.playGame(request, playerId);
		
		if(gameState.isGameOver() && !displayerData.isGameOver()) {
			displayerData = gameService.playGame(request, playerId);
		}
		
		return new ResponseEntity<DisplayerData>(displayerData, HttpStatus.OK);
	}
	
	@GetMapping("/save")
	public ResponseEntity<Object> saveActualScore(@CookieValue(value = "playerId") String playerId, HttpServletRequest request){
		
		gameService.saveActualScore(request, playerId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/sound", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE })
        public ResponseEntity<InputStreamResource> playAudio(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
		
		File audio =  new File("./sound/Saltarello.wav");
        long length = audio.length();

        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(audio));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(length);
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        
        return new ResponseEntity<InputStreamResource>(inputStreamResource, httpHeaders, HttpStatus.OK);
    }
	
	
}
