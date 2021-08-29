package com.david.giczi.webtris.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.david.giczi.webtris.model.Player;
import com.david.giczi.webtris.service.PlayerService;


@Controller
@RequestMapping("/webtris")
public class WebtrisOperations {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("")
	public String goLoginPage() {
		return "login";
	}
	
	@PostMapping("/player-sign-in")
	public String signInPlayer(HttpServletRequest request, Model model) throws ParseException {
		
		String playerName = request.getParameter("playerName");
		String birthDate = request.getParameter("birthDate");
		//String getSound = request.getParameter("getSound");
		
		if(playerService.validateInputData(playerName, birthDate)) {
			
		Player player = playerService.getPlayerByNameAndBirthDate(playerName, birthDate);
		if(player == null) {
			player = new Player();
			player.setName(playerName);
			player.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate));
			playerService.save(player);
		}
			
		}
		else {
			model.addAttribute("invalid", "INVALID INPUT DATA: "
			+ "Your player name should be at least 3 chars and you should add the date of your birth.");
			return "login";
		}
		
			
		return "gameboard";
	}
	
	
}
