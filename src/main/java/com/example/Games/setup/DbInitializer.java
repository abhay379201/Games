package com.example.Games.setup;

import com.example.Games.modals.Game;
import com.example.Games.modals.Genre;
import com.example.Games.repositories.GameRepository;
import com.example.Games.repositories.GenreRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DbInitializer {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GenreRepository genreRepository;

//    @Autowired
//    ResourceLoader resources;

    @PostConstruct
    void setupGameData() {
        List<GameInfo> gameInfoList = loadObjectList();

        Set<Game> gameSet = new HashSet<>();

        for (GameInfo gameInfo : gameInfoList) {
            String genre = gameInfo.getGenre().trim();
            Set<Genre> genreList = new HashSet<>();
            if (genre.length() > 0) {
                String[] genres = genre.split(",");
                for (String subgenre : genres) {
                    if (!Genre.genres.containsKey(subgenre.trim())) {
                        Genre.genres.put(subgenre.trim(), new Genre(subgenre.trim()));
                    }
                    genreList.add(Genre.genres.get(subgenre.trim()));
                }
            }
//            System.out.println(genreList.size());
            Game game = new Game(gameInfo.getEditors_choice().equals("Y"), gameInfo.getPlatform(), gameInfo.getScore(), gameInfo.getTitle(), genreList);
            gameSet.add(game);

            for (Genre temp : genreList) {
                if (temp.getGames() == null) {
                    temp.setGames(new HashSet<>());
                }
                temp.getGames().add(game);
            }
        }
        genreRepository.saveAll(Genre.genres.values());
        gameRepository.saveAll(gameSet);
    }

    public List<GameInfo> loadObjectList() {
        try {
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();

//            Resource resource = resources.getResource("classpath:gamesc2b2088.csv");

//            Resource resource = new ClassPathResource("gamesc2b2088.csv");
//
//            ObjectReader reader = mapper.readerFor(GameInfo.class).with(csvSchema);
//
//            MappingIterator<GameInfo> readValues = reader.readValues(resource.getInputStream());

//            File file = resources.getResource("classpath:gamesc2b2088.csv").getFile();
            File file = new ClassPathResource("gamesc2b2088.csv").getFile();
            MappingIterator<GameInfo> readValues = mapper.readerFor(GameInfo.class).with(csvSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}