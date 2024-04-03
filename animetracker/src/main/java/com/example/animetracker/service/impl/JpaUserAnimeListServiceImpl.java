package com.example.animetracker.service.impl;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.AnimeListStatus;
import com.example.animetracker.model.UserAnimeList;
import com.example.animetracker.repository.JpaUserAnimeListRepository;
import com.example.animetracker.service.UserAnimeListService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "postgresUserAnimeListService")
public class JpaUserAnimeListServiceImpl implements UserAnimeListService {

    private final JpaUserAnimeListRepository jpaUserAnimeListRepository;

    public JpaUserAnimeListServiceImpl(JpaUserAnimeListRepository jpaUserAnimeListRepository) {
        this.jpaUserAnimeListRepository = jpaUserAnimeListRepository;
    }

    // Get all anime for a specific user (userId)
    @Override
    public List<UserAnimeList> getUserListAnime(Integer userId) {
        List<UserAnimeList> anime = new ArrayList<>();
        jpaUserAnimeListRepository.findById(userId).ifPresent(a -> anime.add(a));
        return anime;
    }

    // Get details of a specific anime (animeId) from the user's list (userId)
    @Override
    public Optional<UserAnimeList> getSpecificUserListAnime(Integer userId, Integer animeId) {
        return Optional.empty();
    }

    // Add a new anime entry for a user
    @Override
    public UserAnimeList createUserAnimeListEntry(Integer userId, Integer animeId, AnimeListStatus animeListStatus) {
        return null;
    }

    // Update details of an anime in the user's list
    @Override
    public UserAnimeList updateUserAnimeListEntry(Integer userId, Integer animeId, UserAnimeList updateData) {
        return null;
    }

    // Delete an anime from the user's list
    @Override
    public void deleteUserAnimeListEntry(Integer userId, Integer animeId) {
        Optional<UserAnimeList> anime = jpaUserAnimeListRepository.findById(userId);
        jpaUserAnimeListRepository.deleteById(anime.get().getAnimeId());
    }
}
