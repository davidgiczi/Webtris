package com.david.giczi.webtris.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
public class SignInOperations {

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

	@PostMapping("/player-sign-in")
	public String signInPlayer(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rdAttr, Model model) throws ParseException {

		String playerName = request.getParameter("playerName");
		String birthDate = request.getParameter("birthDate");
		
		if (playerService.validateInputData(playerName, birthDate)) {
			
			Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
			Player player = playerService.getPlayerByNameAndBirthDate(playerName, birthDate);

			if (player == null) {
				player = new Player();
				player.setName(playerName);
				player.setBirthDate(dateOfBirth);
				player.setScoreDate(new Date(System.currentTimeMillis()));
				playerService.save(player);
			}
			
			Long playerId = playerService.getPlayerIdByNameAndBirthDate(playerName, dateOfBirth);
			
			Cookie cookie = new Cookie("playerId", String.valueOf(playerId));
			
			response.addCookie(cookie);

			model.addAttribute("welcome", "Hi " + player.getName() + ", you're welcome to W E B T R I S Game!\n"
					+ "Your highest score is: " + player.getScore());

		} else {
			rdAttr.addAttribute("invalid", true);
			return "redirect:";
		}

		return "gameboard";
	}

}
