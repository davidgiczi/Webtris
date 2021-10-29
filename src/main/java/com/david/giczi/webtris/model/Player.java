package com.david.giczi.webtris.model;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Players")
public class Player implements Comparable<Player> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int score;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private ZonedDateTime scoreDate;
	
	public Player() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ZonedDateTime getScoreDate() {
		
		return scoreDate;
	}

	public void setScoreDate(ZonedDateTime scoreDate) {
		this.scoreDate = scoreDate;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}


	@Override
	public int compareTo(Player o) {
		return this.getScore() < o.getScore() ? 1 : this.getScore() == o.getScore() ? 0 : -1;
	}
	
}
