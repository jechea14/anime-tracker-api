package com.example.animetracker.repository;

import com.example.animetracker.model.UserAnimeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnimeListRepository extends JpaRepository<UserAnimeList, Integer> {

}
