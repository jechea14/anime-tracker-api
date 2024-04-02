package com.example.animetracker.repository;

import com.example.animetracker.model.Anime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAnimeRepository {
    // Pretend this is the database
    private static final List<Anime> db = new ArrayList<>();

    // Initialize some Anime
    static {
        db.add(new Anime(1, "Naruto"));
        db.add(new Anime(2, "Bleach"));
        db.add(new Anime(3, "One Piece"));
    }

    // Save an anime
    public void createAnime(Anime anime) {
        db.add(anime);
    }

    // Get all anime
    public List<Anime> getAllAnime() {
        return List.copyOf(db);
    }

    // Get one anime
    public Anime getAnime(Integer id) {
        return db
                .stream()
                .filter(anime -> id.equals(anime.getId()))
                .findFirst()
                .orElseThrow();
    }

    // Update anime
    public void updateAnime(Anime anime, Integer id) {

    }

    // Delete anime
    public void deleteAnimeById(Integer id) {
        Anime anime = db
                .stream()
                .filter(a -> id.equals(a.getId()))
                .findFirst()
                .orElseThrow();
        db.remove(anime);
    }

}
