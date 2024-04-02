package com.example.animetracker.service.impl;

import com.example.animetracker.model.Anime;
import com.example.animetracker.repository.InMemoryAnimeRepository;
import com.example.animetracker.service.AnimeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "inMemory")
public class InMemoryAnimeServiceImpl implements AnimeService {
    private final InMemoryAnimeRepository inMemoryAnimeRepository;

    public InMemoryAnimeServiceImpl(InMemoryAnimeRepository inMemoryAnimeRepository) {
        this.inMemoryAnimeRepository = inMemoryAnimeRepository;
    }

    @Override
    public void createAnime(Anime anime) {
        inMemoryAnimeRepository.createAnime(anime);
    }

    @Override
    public List<Anime> getAllAnime() {
        return inMemoryAnimeRepository.getAllAnime();
    }

    @Override
    public Optional<Anime> getAnime(Integer id) {
        return Optional.ofNullable(inMemoryAnimeRepository.getAnime(id));
    }

    @Override
    public void updateAnime(Anime anime, Integer id) {
        inMemoryAnimeRepository.updateAnime(anime, id);
    }

    @Override
    public void deleteAnimeById(Integer id) {
        inMemoryAnimeRepository.deleteAnimeById(id);
    }
}
