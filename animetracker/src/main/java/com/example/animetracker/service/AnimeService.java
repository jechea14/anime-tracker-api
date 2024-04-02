package com.example.animetracker.service;


import com.example.animetracker.model.Anime;

import java.util.List;
import java.util.Optional;

public interface AnimeService {
    // save an anime
    void createAnime(Anime anime);

    // get all anime
    List<Anime> getAllAnime();

    // get an anime
    Optional<Anime> getAnime(Integer id);

    // Update an anime
    void updateAnime(Anime anime, Integer id);

    // Delete an anime
    void deleteAnimeById(Integer id);


}
