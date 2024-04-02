package com.example.animetracker.repository;

import com.example.animetracker.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAnimeRepository extends JpaRepository<Anime, Integer> {
}
