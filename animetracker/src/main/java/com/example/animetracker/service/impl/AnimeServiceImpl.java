package com.example.animetracker.service.impl;

import com.example.animetracker.model.Anime;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.service.AnimeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "postgresAnimeService")
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeServiceImpl(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    // Create anime
    @Override
    public void createAnime(Anime anime) {
        Optional<Anime> animeOptional = animeRepository.findByTitle(anime.getTitle());

        if (animeOptional.isPresent()) {
            throw new IllegalStateException("Anime already exists");
        }

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

        animeOptional.setTitle(anime.getTitle());
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
