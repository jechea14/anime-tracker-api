package com.example.animetracker.service;

import com.example.animetracker.dto.AnimeDTO;
import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.model.Anime;
import com.example.animetracker.model.ApplicationUser;
import com.example.animetracker.model.UserAnimeListEntry;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.UserAnimeListEntryRepository;
import com.example.animetracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserAnimeLisEntryServiceTests {

    @Mock
    private UserAnimeListEntryRepository userAnimeListEntryRepository;

    @Mock
    private AnimeRepository animeRepository;

    @Mock
    private UserRepository userRepository;

    private Anime anime;
    private UserAnimeListEntry userAnimeListEntry;
    private AnimeDTO animeDTO;
    private UserAnimeListEntryDTO userAnimeListEntryDTO;

    @BeforeEach
    public void init() {
        anime = Anime.builder().title("Naruto").episodeCount(500).build();
        animeDTO = AnimeDTO.builder().title("Naruto").build();
        userAnimeListEntry = UserAnimeListEntry.builder()
                .applicationUser(new ApplicationUser(1, "admin", "password", ))
                .anime(anime)
                .watchStatus("completed")
                .episodesCompleted(500)
                .build();
    }


}
