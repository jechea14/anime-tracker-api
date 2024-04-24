package com.example.animetracker.service;

import com.example.animetracker.model.Genre;
import com.example.animetracker.repository.GenreRepository;
import com.example.animetracker.service.impl.GenreServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTests {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    public void GenreService_CreateGenre_ReturnsGenre() {

        // arrange
        Genre genre = Genre.builder()
                .name("Action")
                .build();

        // act
        when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(genre);
        Genre savedGenre = genreService.createGenre(genre);

        // assert
        Assertions.assertThat(savedGenre).isNotNull();

    }

    @Test
    public void GenreService_GetAllGenres_ReturnsAllGenres() {
        // arrange - no need to create genres or mock repository

        // act
        List<Genre> genresReturn = genreService.getAllGenres();

        // assert
        Assertions.assertThat(genresReturn).isNotNull();
    }

    @Test
    public void GenreService_GetGenre_ReturnsGenre() {
        // arrange
        Genre genre = Genre.builder()
                .name("Action")
                .build();
        // act
        when(genreRepository.findById(1)).thenReturn(Optional.ofNullable(genre));
        Genre savedGenre = genreService.getGenre(1);

        // assert
        Assertions.assertThat(savedGenre).isNotNull();
    }

    @Test
    public void GenreService_UpdateGenre_ReturnsGenre() {
        // arrange
        Genre genre = Genre.builder()
                .name("Actionnn")
                .build();

        // act
        when(genreRepository.findById(1)).thenReturn(Optional.ofNullable(genre));
        when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(genre);

        Genre savedGenre = genreService.updateGenre(1, genre);

        // assert
        Assertions.assertThat(savedGenre).isNotNull();
    }

    @Test
    public void GenreService_DeleteGenre_ReturnsGenre() {
        // arrange
        Genre genre = Genre.builder()
                .name("Actionnn")
                .build();

        // act
        when(genreRepository.findById(1)).thenReturn(Optional.ofNullable(genre));

        // assert
        assertAll(() -> genreService.deleteGenre(1));
    }

}
