package com.example.animetracker.service.impl;

import com.example.animetracker.model.Genre;
import com.example.animetracker.repository.GenreRepository;
import com.example.animetracker.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(Integer id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);

        if (genreOptional.isEmpty()) {
            throw new IllegalStateException("Genre with id " + id + " does not exist");
        }

        return genreOptional.get();
    }

    @Override
    public void createGenre(Genre genre) {
        Optional<Genre> genreOptional = genreRepository.findByName(genre.getName());

        if (genreOptional.isPresent()) {
            throw new IllegalStateException("Genre already exists");
        }

        genreRepository.save(genre);

    }

    @Override
    public void updateGenre(Integer id, Genre genre) {
        Optional<Genre> genreOptional = genreRepository.findById(id);

        if (genreOptional.isEmpty()) {
            throw new IllegalStateException("Genre with id " + id + " does not exist.");
        }

        Genre g = genreOptional.get();
        g.setName(genre.getName());
        genreRepository.save(g);

    }

    @Override
    public void deleteGenre(Integer id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);

        if (genreOptional.isEmpty()) {
            throw new IllegalStateException("Genre with id " + id + " does not exist");
        }

        genreRepository.delete(genreOptional.get());

    }
}
