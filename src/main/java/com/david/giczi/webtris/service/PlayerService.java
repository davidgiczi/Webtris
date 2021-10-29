package com.david.giczi.webtris.service;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.david.giczi.webtris.model.Player;
import com.david.giczi.webtris.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepo;
	
	public void save(Player player) {
		playerRepo.save(player);
	}
	
	public Player getPlayerByName(String name) throws ParseException {
		
		Player player = playerRepo.getPlayerByName(name);
		
		return player;
	}
	
	public Long getPlayerIdByName(String name) {
		return playerRepo.getPlayerIdByName(name);
	}
	
	public boolean validateInputData(String name) {
		
		return name.trim().length() > 3 && getAllPlayers().stream().allMatch(p -> !p.getName().equals(name));
		
	}
	
	public boolean validateRegisteredName(String name) {
		
		return getAllPlayers().stream().anyMatch(p -> p.getName().equals(name));
		
	}
	
	public int getScoreById(String id) {
		return playerRepo.getScoreById(id);
	}
	
	public List<Player> getAllPlayers(){
		return playerRepo.findAll();
	}
	
	public Player getPlayerById(String id) {
		return playerRepo.findById(Long.valueOf(id)).get();
	}
}
