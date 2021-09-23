package com.david.giczi.webtris.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public Player getPlayerByNameAndBirthDate(String name, String birthDate) throws ParseException {
		
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
		Player player = playerRepo.getPlayerByNameAndBirthDate(name, dateOfBirth);
		
		return player;
	}
	
	public Long getPlayerIdByNameAndBirthDate(String name, Date birthDate) {
		return playerRepo.getPlayerIdByNameAndBirthDate(name, birthDate);
	}
	
	public boolean validateInputData(String name, String birthdate) {
		return name.trim().length() > 3 && !birthdate.isEmpty();
	}
	
	public int getScoreById(String id) {
		return playerRepo.getScoreById(id);
	}
	
	public List<Player> getAllPlayers(){
		return playerRepo.findAll();
	}
}
