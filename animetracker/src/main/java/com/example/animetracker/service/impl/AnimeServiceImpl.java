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

    @Override
    public void createAnime(Anime anime) {
        Optional<Anime> animeOptional = animeRepository.findByTitle(anime.getTitle());

        if (animeOptional.isPresent()) {
            throw new IllegalStateException("Anime already exists");
        }

        animeRepository.save(anime);
    }

    @Override
    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    @Override
    public Optional<Anime> getAnime(Integer id) {
        doesAnimeExist(id);
        return animeRepository.findById(id);
    }

    @Override
    public void updateAnime(Anime anime, Integer id) {
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isEmpty()) {
            throw new IllegalStateException("Anime with id " + id + " does not exist");
        }

        animeRepository.save(animeOptional.get());
    }

    @Override
    public void deleteAnimeById(Integer id) {
        doesAnimeExist(id);
        animeRepository.deleteById(id);
    }

    private void doesAnimeExist(Integer animeId) {
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (animeOptional.isEmpty()) {
            throw new IllegalStateException("Anime with id " + animeId + " does not exist");
        }
    }
}
