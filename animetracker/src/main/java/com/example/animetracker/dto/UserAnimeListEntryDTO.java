package com.example.animetracker.dto;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.WatchStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAnimeListEntryDTO {
        private Integer id;
        private AnimeDTO anime;
        private String watchStatus;
        private Integer episodesCompleted;
}
