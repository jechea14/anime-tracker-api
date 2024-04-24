package com.example.animetracker.service;

import com.example.animetracker.model.Genre;

import java.util.List;

public interface GenreService {

    // Get all genres
    List<Genre> getAllGenres();

    // Get one genre
    Genre getGenre(Integer id);

    // Create genre
    Genre createGenre(Genre genre);

    // Update genre
    Genre updateGenre(Integer id, Genre genre);

    // Delete genre
    void deleteGenre(Integer id);
}
