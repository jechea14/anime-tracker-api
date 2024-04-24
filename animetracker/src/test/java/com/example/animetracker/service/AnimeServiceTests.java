package com.example.animetracker.service;

import com.example.animetracker.dto.AnimePagination;
import com.example.animetracker.model.Anime;
import com.example.animetracker.model.Genre;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.GenreRepository;
import com.example.animetracker.service.impl.AnimeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = AnimetrackerApplication.class) //tells it to use the entire project to run
public class AnimeServiceTests {

    @Mock
    private AnimeRepository animeRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private AnimeServiceImpl animeService;

    @Test
    public void AnimeService_CreateAnime_ReturnsAnime() {
        // arrange
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(1, "Action");
        genres.add(genre);

        Anime anime = Anime.builder()
                .title("Naruto")
                .episodeCount(500)
                .genres(genres)
                .build();

        // act
        when(animeRepository.save(Mockito.any(Anime.class))).thenReturn(anime);
        Anime savedAnime = animeService.createAnime(anime);

        // assert
        Assertions.assertThat(savedAnime).isNotNull();
    }

//    @Test
//    public void AnimeService_CreateDuplicateAnime_ThrowsException() {
//        // arrange
//        Set<Genre> genres = new HashSet<>();
//        Genre genre = new Genre(1, "Action");
//        genres.add(genre);
//
//        Anime anime = Anime.builder()
//                .title("Naruto")
//                .episodeCount(500)
//                .genres(genres)
//                .build();
//
//        Anime anime1 = Anime.builder()
//                .title("Naruto")
//                .episodeCount(500)
//                .genres(genres)
//                .build();
//
//        // act
//        when(animeRepository.save(Mockito.any(Anime.class))).thenReturn(anime);
//        animeService.createAnime(anime);
//        Anime savedAnime1 = animeService.createAnime(anime1);
//
//        // assert
//        Assertions.assertThat(savedAnime1)
//    }
    @Test
    public void AnimeService_GetAllAnime_ReturnsAllAnime() {
        // create fake class
//        AnimePagination animeReturn = Mockito.mock(AnimePagination.class);
        // create fake class
        Page<Anime> animes = Mockito.mock(Page.class);

        when(animeRepository.findAll(Mockito.any(Pageable.class))).thenReturn(animes);

        AnimePagination savedAnime = animeService.getAllAnime(1, 10);

        Assertions.assertThat(savedAnime).isNotNull();
    }

    @Test
    public void AnimeService_GetAnimeById_ReturnsAnime() {
        // arrange
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(1, "Action");
        genres.add(genre);

        Anime anime = Anime.builder()
                .id(1)
                .title("Naruto")
                .episodeCount(500)
                .genres(genres)
                .build();

        // act
        when(animeRepository.findById(1)).thenReturn(Optional.ofNullable(anime));
        Optional<Anime> savedAnime = animeService.getAnime(1);

        // assert
        Assertions.assertThat(savedAnime).isNotNull();
    }

    @Test
    public void AnimeService_UpdateAnime_ReturnsAnime() {
        // arrange
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(1, "Action");
        genres.add(genre);

        Anime anime = Anime.builder()
                .id(1)
                .title("Narutoooo")
                .episodeCount(500)
                .genres(genres)
                .build();

        // act
        when(animeRepository.findById(1)).thenReturn(Optional.ofNullable(anime));
        when(animeRepository.save(Mockito.any(Anime.class))).thenReturn(anime);


        Anime savedAnime = animeService.updateAnime(anime, 1);

        // assert
        Assertions.assertThat(savedAnime).isNotNull();
    }

    @Test
    public void AnimeService_DeleteAnime_ReturnsAnime() {
        // arrange
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(1, "Action");
        genres.add(genre);

        Anime anime = Anime.builder()
                .id(1)
                .title("Naruto")
                .episodeCount(500)
                .genres(genres)
                .build();

        // act
        when(animeRepository.findById(1)).thenReturn(Optional.ofNullable(anime));

        // assert
        assertAll(() -> animeService.deleteAnimeById(1));
    }


}
