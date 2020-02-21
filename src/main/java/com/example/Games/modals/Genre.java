package com.example.Games.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class Genre {
    @JsonIgnore
    @Id
    @GeneratedValue
    private int id;

    private String value;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private Set<Game> games;

    public static Map<String, Genre> genres = new HashMap<>();

    public Genre(String value) {
        this.value = value;
    }

    public Genre() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
