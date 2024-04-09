package com.example.animetracker.dto;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.WatchStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UserAnimeListEntryDTO(
        Integer id,
        Anime anime,
        @Enumerated(EnumType.STRING)
        WatchStatus watchStatus
) {

}
