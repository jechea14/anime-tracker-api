package com.example.animetracker.controller;

import com.example.animetracker.model.Genre;
import com.example.animetracker.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Get all genres
    @GetMapping("")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    // Get a genre
    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") Integer id) {
        return genreService.getGenre(id);
    }

    // Create a genre
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createGenre(@RequestBody Genre genre) {
        genreService.createGenre(genre);
    }

    // Update a genre
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateGenre(@RequestBody Genre genre, @PathVariable("id") Integer id) {
        genreService.updateGenre(id, genre);
    }

    // Delete a genre
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Integer id) {
        genreService.deleteGenre(id);
    }

}
