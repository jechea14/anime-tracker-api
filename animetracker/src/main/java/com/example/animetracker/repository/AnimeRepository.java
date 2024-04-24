package com.example.animetracker.repository;

import com.example.animetracker.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer>, PagingAndSortingRepository<Anime, Integer> {
    Optional<Anime> findByTitle(String title);
}
