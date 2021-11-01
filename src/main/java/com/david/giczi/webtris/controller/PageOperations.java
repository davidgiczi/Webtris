package com.david.giczi.webtris.controller;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.david.giczi.webtris.model.Player;
import com.david.giczi.webtris.service.PlayerService;

@Controller
@RequestMapping("/webtris")
public class PageOperations {

	@Autowired
	private PlayerService playerService;

	@RequestMapping("")
	public String goLoginPage() {
		return "login";
	}
	
	@RequestMapping("ranklist")
	public String goRankListPage(Model model) {
		
		List<Player> players = playerService.getAllPlayers();
		Collections.sort(players);
		model.addAttribute("players", players);
	
		return "ranklist";
	}
	
	@PostMapping("/player-sign-up")
	public String signUpPlayer(HttpServletRequest request, RedirectAttributes rdAttr) {
		
	String name = request.getParameter("regName");
	
	if(playerService.validateInputData(name)) {
		Player player = new Player();
		player.setName(name);
		player.setScore(0);
		long m = System.currentTimeMillis();
		player.setScoreDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli(m), ZoneId.systemDefault()));
		playerService.save(player);
		return "redirect:";
	}
	
	rdAttr.addAttribute("invalidSignUp", true);
	return "redirect:";
	}
	

	@PostMapping("/player-sign-in")
	public String signInPlayer(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rdAttr, Model model) throws ParseException {

		String playerName = request.getParameter("playerName");
		
		if(playerService.validateRegisteredName(playerName)) {
		
		Player player = playerService.getPlayerByName(playerName);

		Long playerId = playerService.getPlayerIdByName(playerName);
			
		Cookie cookie = new Cookie("playerId", String.valueOf(playerId));
			
		response.addCookie(cookie);

		model.addAttribute("welcome", "Hi " + player.getName() + ", you're welcome to W E B T R I S Game!\n"
					+ "Your highest score is: " + player.getScore());
		}
		else {
			rdAttr.addAttribute("invalidSignIn", true);
			return "redirect:";
		}


		return "gameboard";
	}


}
