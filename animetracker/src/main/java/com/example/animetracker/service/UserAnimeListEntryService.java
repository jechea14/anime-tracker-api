package com.example.animetracker.service;

import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.dto.UserDTO;
import com.example.animetracker.model.UserAnimeListEntry;
import com.example.animetracker.model.WatchStatus;

import java.util.List;

public interface UserAnimeListEntryService {
    // Gets and returns a list of anime of a specific user
    List<UserAnimeListEntryDTO> getUserAnimeList(Integer userId);

    // Gets and returns an anime from a specific user
    UserAnimeListEntryDTO getSpecificUserListAnime(Integer userId, Integer animeId);

    // Creates an anime entry from a specific user
    UserAnimeListEntry createUserAnimeListEntry(Integer userId, Integer animeId, WatchStatus watchStatus);

    // Updates an anime entry from a specific user
    void updateUserAnimeListEntry(Integer userId, Integer animeId, WatchStatus status);

    // Deletes an anime entry from a specific user
    void deleteUserAnimeListEntry(Integer userId, Integer animeId);

    // Get users who have watched a specific anime
    List<UserDTO> getUsersWhoWatchedAnime(Integer animeId);

    // Get anime entries with a specific status for a user
    List<UserAnimeListEntryDTO> getUserAnimeListByStatus(Integer userId, WatchStatus status);

}
