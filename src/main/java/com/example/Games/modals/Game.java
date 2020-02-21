package com.example.Games.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Game {

    @JsonIgnore
    @Id
    @GeneratedValue
    private int id;

    private boolean editors_choice;

    private String platform;

    private float score;

    private String title;

    @ManyToMany
    private Set<Genre> genre;

    public Game() {

    }

    public Game(boolean editors_choice, String platform, float score, String title, Set<Genre> genre) {
        this.editors_choice = editors_choice;
        this.platform = platform;
        this.score = score;
        this.title = title;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isEditors_choice() {
        return editors_choice;
    }

    public void setEditors_choice(boolean editors_choice) {
        this.editors_choice = editors_choice;
    }
}