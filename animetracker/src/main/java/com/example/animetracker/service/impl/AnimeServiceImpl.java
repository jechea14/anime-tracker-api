package com.example.animetracker.service.impl;

import com.example.animetracker.dto.AnimePagination;
import com.example.animetracker.model.Anime;
import com.example.animetracker.model.Genre;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.GenreRepository;
import com.example.animetracker.service.AnimeService;
import com.example.animetracker.service.GenreService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Anime createAnime(Anime anime) {
        Optional<Anime> animeOptional = animeRepository.findByTitle(anime.getTitle());

        if (animeOptional.isPresent()) {
            throw new EntityExistsException("Anime already exists");
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
        return anime;
    }

    // Get all anime
//    @Override
//    public List<Anime> getAllAnime() {
//        return animeRepository.findAll();
//    }

    // Get all paginated anime
    @Override
    public AnimePagination getAllAnime(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Anime> animes = animeRepository.findAll(pageable);
        List<Anime> listOfAnimes = animes.getContent();

        AnimePagination animePagination = new AnimePagination();
        animePagination.setContent(listOfAnimes);
        animePagination.setPageNo(animes.getNumber());
        animePagination.setPageSize(animes.getSize());
        animePagination.setTotalElements(animes.getTotalElements());
        animePagination.setTotalPages(animes.getTotalPages());
        animePagination.setLast(animes.isLast());

        return animePagination;
    }

    // Get anime
    @Override
    public Optional<Anime> getAnime(Integer id) {
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isEmpty()) {
            throw new EntityNotFoundException("Anime with id" + id + " does not exist");
        }

        return animeRepository.findById(id);
    }

    // Update anime
    @Override
    public Anime updateAnime(Anime anime, Integer id) {
        Anime animeOptional = animeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Anime with id " + id + " does not exist"));

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
        return animeOptional;
    }

    // Delete anime
    @Override
    public void deleteAnimeById(Integer id) {
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isEmpty()) {
            throw new EntityNotFoundException("Anime with id" + id + " does not exist");
        }
        animeRepository.deleteById(id);
    }

}
