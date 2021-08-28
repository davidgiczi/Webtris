package com.david.giczi.webtris.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SoundService {

	@Bean
	public SoundPlayer getPlayer() {
		return new SoundPlayer();
	}
}
