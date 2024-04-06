package com.example.animetracker.dto;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.AnimeListStatus;

public record UserAnimeListDTO (
        Integer id,
        Anime anime,
        String animeListStatus
) {

}
