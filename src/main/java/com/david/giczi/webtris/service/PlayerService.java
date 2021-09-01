package com.david.giczi.webtris.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public Player getPlayerByName(String name)  {
		
		Player player = playerRepo.getPlayerByName(name);
		
		return player;
	}
	
	public boolean validateInputData(String name, String birthdate) {
		return name.trim().length() > 3 && !birthdate.isEmpty();
	}
}
