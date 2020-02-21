package com.example.Games.repositories;

import com.example.Games.modals.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

//    List<Game> getGames();
}
