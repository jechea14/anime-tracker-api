package com.example.animetracker.service;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.AnimeListStatus;
import com.example.animetracker.model.User;
import com.example.animetracker.model.UserAnimeList;

import java.util.List;
import java.util.Optional;

public interface UserAnimeListService {
    List<UserAnimeList> getUserListAnime(Integer userId);

    Optional<UserAnimeList> getSpecificUserListAnime(Integer userId, Integer animeId);

    UserAnimeList createUserAnimeListEntry(Integer userId, Integer animeId, String animeListStatus);

    void updateUserAnimeListEntry(Integer userId, Integer animeId, UserAnimeList updateData);

    void deleteUserAnimeListEntry(Integer userId, Integer animeId);

    // Get users who have watched a specific anime
    List<User> getUsersWhoWatchedAnime(Integer animeId);

    // Get anime entries with a specific status for a user
    List<UserAnimeList> getUserAnimeListByStatus(Integer userId, String status);
//    // get all anime lists for all users
//    List<UserAnimeList> getAllUserAnimeLists();
//
//    // Get and return a list of animelists for one user
//    List<UserAnimeList> getUserListAnime(Integer userId);
//
//    // Create and add a new anime entry to a user's list
//    void createAnimeEntry(Integer userId, Anime anime);
//
//    // Update an anime entry in a user's list
//    void updateAnimeEntry(Integer userId, Integer animeId);
//
//    // Delete an anime entry in a user's list
//    void deleteAnimeEntry(Integer userId, Integer animeId);

}
