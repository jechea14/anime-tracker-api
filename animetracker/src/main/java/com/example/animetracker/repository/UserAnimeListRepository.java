package com.example.animetracker.repository;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.User;
import com.example.animetracker.model.UserAnimeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnimeListRepository extends JpaRepository<UserAnimeList, Integer> {

    List<UserAnimeList> findByUser(User user);
    List<UserAnimeList> findByAnime(Anime anime);
    List<UserAnimeList> findByUserAndAnime(User user, Anime anime);
}
