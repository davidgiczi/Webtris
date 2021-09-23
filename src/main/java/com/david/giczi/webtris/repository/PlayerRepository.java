package com.david.giczi.webtris.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.david.giczi.webtris.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

	
	@Query(value = "select * from players where name like :name and birth_date = :birthdate", nativeQuery = true)
	Player getPlayerByNameAndBirthDate(@Param("name") String name, @Param("birthdate") Date birthDate);
	
	@Query(value = "select id from players where name like :name and birth_date = :birthdate", nativeQuery = true)
	Long getPlayerIdByNameAndBirthDate(@Param("name") String name, @Param("birthdate") Date birthDate);
	
	@Query(value = "select score from players where id = :id" , nativeQuery = true)
	int getScoreById(@Param("id") String playerId);
	
	List<Player> findAll();
	
}
