package com.example.animetracker.service;


import com.example.animetracker.dto.AnimePagination;
import com.example.animetracker.model.Anime;

import java.util.List;
import java.util.Optional;

public interface AnimeService {
    // save an anime
    Anime createAnime(Anime anime);

    // get all anime
//    List<Anime> getAllAnime();

    // get all paginated anime
    AnimePagination getAllAnime(Integer pageNo, Integer pageSize);

    // get an anime
    Optional<Anime> getAnime(Integer id);

    // Update an anime
    Anime updateAnime(Anime anime, Integer id);

    // Delete an anime
    void deleteAnimeById(Integer id);


}
