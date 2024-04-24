package com.example.animetracker.service;

import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.dto.UserAnimeListEntryPagination;
import com.example.animetracker.dto.UserDTO;
import com.example.animetracker.model.UserAnimeListEntry;
import com.example.animetracker.model.WatchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserAnimeListEntryService {
    // Gets and returns a list of anime of a specific user
//    List<UserAnimeListEntryDTO> getUserAnimeList();

    // Gets and returns a list of anime of a specific user with pagination
    UserAnimeListEntryPagination getAllUserAnimeListEntries(Integer pageNo, Integer pageSize);

    // Gets and returns an anime from a specific user
    UserAnimeListEntryDTO getSpecificUserListAnime(Integer animeId);

    // Creates an anime entry from a specific user
    UserAnimeListEntry createUserAnimeListEntry(Integer animeId, UserAnimeListEntry userAnimeListEntry);

    // Updates an anime entry from a specific user
    UserAnimeListEntryDTO updateUserAnimeListEntry(Integer animeId, UserAnimeListEntry userAnimeListEntry);

    // Deletes an anime entry from a specific user
    void deleteUserAnimeListEntry(Integer animeId);

//    // Get users who have watched a specific anime
//    List<UserDTO> getUsersWhoWatchedAnime(Integer animeId);
//
//    // Get anime entries with a specific status for a user
//    List<UserAnimeListEntryDTO> getUserAnimeListByStatus(Integer userId, String status);

}
