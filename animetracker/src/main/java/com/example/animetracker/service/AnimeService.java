package com.example.animetracker.service;

import com.example.animetracker.model.Anime;
import com.example.animetracker.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }


    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    public Optional<Anime> getAnime(Integer id) {
        return animeRepository.findById(id);
    }

    public void createAnime(Anime anime) {
        animeRepository.save(anime);
    }

    public void deleteAnime(Integer id) {
        animeRepository.deleteById(id);
    }

    public void updateAnime(Anime anime, Integer id) {

    }
}
