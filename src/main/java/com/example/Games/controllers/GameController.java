package com.example.Games.controllers;

import com.example.Games.modals.Game;
import com.example.Games.repositories.GameRepository;
import com.example.Games.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/genre/")
    List<Game> getAllGames(@RequestParam(required = false, name = "editors_choice") Boolean isEditorsChoice,
                           @RequestParam(required = false, name = "sort", defaultValue = "raw") String sortBy) {
        List<Game> games = gameRepository.findAll();

        if (isEditorsChoice != null) {
            games = games.stream().filter((game) -> game.isEditors_choice() == isEditorsChoice).collect(Collectors.toList());
        }
        if (sortBy.equals("asc")) {
            games.sort((g1, g2) -> Float.compare(g1.getScore(), g2.getScore()));
        } else if (sortBy.equals("desc")) {
            games.sort((g1, g2) -> Float.compare(g2.getScore(), g1.getScore()));
        }

        System.out.println("Editors choice = " + isEditorsChoice + " sort = " + sortBy);
        return games;
    }

    @PostMapping("/genre/")
    void createGame(@RequestParam(name = "title", required = true) String title,
                    @RequestParam(name = "platform", required = true) String platform,
                    @RequestParam(name = "score", required = true) Float score,
                    @RequestParam(name = "genre", required = false) Set<String> genres,
                    @RequestParam(name = "editors_choice", required = false, defaultValue = "false") boolean isEditorsChoice) {

    }
}