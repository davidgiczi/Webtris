package com.david.giczi.webtris.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.david.giczi.webtris.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

	
	@Query(value = "select * from players where name like :name", nativeQuery = true)
	Player getPlayerByName(@Param("name") String name);
	
	@Query(value = "select id from players where name like :name", nativeQuery = true)
	Long getPlayerIdByName(@Param("name") String name);
	
	@Query(value = "select score from players where id = :id" , nativeQuery = true)
	int getScoreById(@Param("id") String playerId);
	
	List<Player> findAll();
	
	Optional<Player> findById(Long id);
}
