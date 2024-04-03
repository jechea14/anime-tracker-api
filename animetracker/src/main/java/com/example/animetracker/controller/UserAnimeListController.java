package com.example.animetracker.controller;


import com.example.animetracker.model.Anime;
import com.example.animetracker.model.AnimeListStatus;
import com.example.animetracker.model.UserAnimeList;
import com.example.animetracker.service.UserAnimeListService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserAnimeListController {

    private final UserAnimeListService userAnimeListService;

    public UserAnimeListController(@Qualifier(value = "postgresUserAnimeListService") UserAnimeListService userAnimeListService) {
        this.userAnimeListService = userAnimeListService;
    }

    // Get all anime for a specific user (userId)
    @GetMapping("/{userId}/anime")
    public List<UserAnimeList> getUserListAllAnime(@PathVariable("userId") Integer userId) {
        return userAnimeListService.getUserListAnime(userId);
    }

    // Get details of a specific anime (animeId) from the user's list (userId)
    @GetMapping("/{userId}/anime/{animeId}")
    public Optional<UserAnimeList> getUserListSpecificAnime(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId) {
        return userAnimeListService.getSpecificUserListAnime(userId, animeId);
    }

    // Add a new anime entry for a user
    @PostMapping("/{userId}/anime/{animeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAnimeList createUserListAnimeEntry(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId, @RequestBody AnimeListStatus animeListStatus) {
        return userAnimeListService.createUserAnimeListEntry(userId, animeId, animeListStatus);
    }

    // Update details of an anime in the user's list
    @PutMapping("/{userId}/anime/{animeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserAnimeList updateUserAnimeListEntry(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId, @RequestBody UserAnimeList updateData) {
        return userAnimeListService.updateUserAnimeListEntry(userId, animeId, updateData);
    }

    // Delete an anime from the user's list
    @DeleteMapping("/{userId}/anime/{animeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserListAnimeEntry(@PathVariable("userId") Integer userId, @PathVariable("animeId") Integer animeId) {
        userAnimeListService.deleteUserAnimeListEntry(userId, animeId);
    }

}
