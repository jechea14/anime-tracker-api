package com.example.animetracker.service.impl;

import com.example.animetracker.model.Anime;
import com.example.animetracker.repository.JpaAnimeRepository;
import com.example.animetracker.service.AnimeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "postgresAnimeService")
public class JpaAnimeServiceImpl implements AnimeService {

    private final JpaAnimeRepository jpaAnimeRepository;

    public JpaAnimeServiceImpl(JpaAnimeRepository jpaAnimeRepository) {
        this.jpaAnimeRepository = jpaAnimeRepository;
    }

    @Override
    public void createAnime(Anime anime) {
        jpaAnimeRepository.save(anime);
    }

    @Override
    public List<Anime> getAllAnime() {
        return jpaAnimeRepository.findAll();
    }

    @Override
    public Optional<Anime> getAnime(Integer id) {
        return jpaAnimeRepository.findById(id);
    }

    @Override
    public void updateAnime(Anime anime, Integer id) {
        Optional<Anime> animeOptional = jpaAnimeRepository.findById(id);
        if (!animeOptional.isPresent()) {
            throw new IllegalStateException("Anime with id " + id + " does not exist");
        }

        jpaAnimeRepository.save(animeOptional.get());
    }

    @Override
    public void deleteAnimeById(Integer id) {
        jpaAnimeRepository.deleteById(id);
    }
}
