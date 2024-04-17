package com.example.animetracker.controller;


import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.dto.UserDTO;
import com.example.animetracker.model.UserAnimeListEntry;
import com.example.animetracker.model.WatchStatus;
import com.example.animetracker.service.UserAnimeListEntryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserAnimeListEntryController {

    private final UserAnimeListEntryService userAnimeListEntryService;

    public UserAnimeListEntryController(@Qualifier(value = "postgresUserAnimeListService") UserAnimeListEntryService userAnimeListEntryService) {
        this.userAnimeListEntryService = userAnimeListEntryService;
    }

    // Get all anime for a specific user (userId)
    @GetMapping("/{userId}/anime")
    public List<UserAnimeListEntryDTO> getUserListAllAnime(@PathVariable("userId") Integer userId) {
        return userAnimeListEntryService.getUserAnimeList(userId);
    }

    // Get details of a specific anime (animeId) from the user's list (userId)
    @GetMapping("/{userId}/anime/{animeId}")
    public UserAnimeListEntryDTO getUserListSpecificAnime(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId) {
        return userAnimeListEntryService.getSpecificUserListAnime(userId, animeId);
    }

    // Add a new anime entry for a user
    @PostMapping("/{userId}/anime/{animeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAnimeListEntry createUserListAnimeEntry(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId, @RequestBody UserAnimeListEntry entry) {
        return userAnimeListEntryService.createUserAnimeListEntry(userId, animeId, entry);
    }

    // Update details of an anime in the user's list
    @PutMapping("/{userId}/anime/{animeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserAnimeListEntry(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId, @RequestBody UserAnimeListEntry entry) {
        userAnimeListEntryService.updateUserAnimeListEntry(userId, animeId, entry);
    }

    // Delete an anime from the user's list
    @DeleteMapping("/{userId}/anime/{animeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserListAnimeEntry(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId) {
        userAnimeListEntryService.deleteUserAnimeListEntry(userId, animeId);
    }

    @GetMapping("/anime/{animeId}")
    public List<UserDTO> getUsersWhoWatchedAnime(@PathVariable("animeId") Integer animeId) {
        return userAnimeListEntryService.getUsersWhoWatchedAnime(animeId);
    }

    @GetMapping("/{userId}/anime/status")
    public List<UserAnimeListEntryDTO> getUserAnimeListByStatus(@PathVariable("userId") Integer userId, @RequestBody String status) {
        return userAnimeListEntryService.getUserAnimeListByStatus(userId, status);
    }

}
