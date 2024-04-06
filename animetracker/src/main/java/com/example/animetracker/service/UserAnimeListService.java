package com.example.animetracker.service;

import com.example.animetracker.dto.UserAnimeListDTO;
import com.example.animetracker.dto.UserDTO;
import com.example.animetracker.model.AnimeListStatus;
import com.example.animetracker.model.User;
import com.example.animetracker.model.UserAnimeList;

import java.util.List;
import java.util.Optional;

public interface UserAnimeListService {
    // Gets and returns a list of anime of a specific user
    List<UserAnimeListDTO> getUserAnimeList(Integer userId);

    // Gets and returns an anime from a specific user
    Optional<UserAnimeListDTO> getSpecificUserListAnime(Integer userId, Integer animeId);

    // Creates an anime entry from a specific user
    UserAnimeList createUserAnimeListEntry(Integer userId, Integer animeId, String watchStatus);

    // Updates an anime entry from a specific user
    void updateUserAnimeListEntry(Integer userId, Integer animeId, UserAnimeList updateData);

    // Deletes an anime entry from a specific user
    void deleteUserAnimeListEntry(Integer userId, Integer animeId);

    // Get users who have watched a specific anime
    List<UserDTO> getUsersWhoWatchedAnime(Integer animeId);

    // Get anime entries with a specific status for a user
    List<UserAnimeListDTO> getUserAnimeListByStatus(Integer userId, String status);

}
