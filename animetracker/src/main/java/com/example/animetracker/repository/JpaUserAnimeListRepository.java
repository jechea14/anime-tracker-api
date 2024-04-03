package com.example.animetracker.repository;

import com.example.animetracker.model.UserAnimeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAnimeListRepository extends JpaRepository<UserAnimeList, Integer> {
}
