package com.example.animetracker.service.impl;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.Genre;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.GenreRepository;
import com.example.animetracker.service.AnimeService;
import com.example.animetracker.service.GenreService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier(value = "postgresAnimeService")
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;
    private final GenreRepository genreRepository;

    public AnimeServiceImpl(AnimeRepository animeRepository, GenreRepository genreRepository) {
        this.animeRepository = animeRepository;
        this.genreRepository = genreRepository;
    }

    // Create anime
    @Override
    public void createAnime(Anime anime) {
        Optional<Anime> animeOptional = animeRepository.findByTitle(anime.getTitle());

        if (animeOptional.isPresent()) {
            throw new IllegalStateException("Anime already exists");
        }

        Set<Genre> genres = new HashSet<>();
        for (Genre genre : anime.getGenres()) {
            Optional<Genre> managedGenre = genreRepository.findById(genre.getId());
            if (managedGenre.isPresent()) {
                genres.add(managedGenre.get());
            }
        }
        anime.setGenres(genres);
        animeRepository.save(anime);
    }

    // Get all anime
    @Override
    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    // Get anime
    @Override
    public Optional<Anime> getAnime(Integer id) {
        doesAnimeExist(id);
        return animeRepository.findById(id);
    }

    // Update anime
    @Override
    public void updateAnime(Anime anime, Integer id) {
        Anime animeOptional = animeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Anime with id " + id + " does not exist"));

        if (anime.getTitle() != null) {
            animeOptional.setTitle(anime.getTitle());
        }

        if (anime.getGenres() != null) {
            animeOptional.setGenres(anime.getGenres());
        }

        if (anime.getEpisodeCount() != null) {
            animeOptional.setEpisodeCount(anime.getEpisodeCount());
        }

        animeRepository.save(animeOptional);
    }

    // Delete anime
    @Override
    public void deleteAnimeById(Integer id) {
        doesAnimeExist(id);
        animeRepository.deleteById(id);
    }

    // Helper method
    private void doesAnimeExist(Integer animeId) {
        boolean exists = animeRepository.existsById(animeId);
        if (!exists) {
            throw new IllegalStateException("Anime with id " + animeId + " does not exist");
        }
    }
}
