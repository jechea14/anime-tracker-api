package com.example.animetracker.controller;


import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.dto.UserAnimeListEntryPagination;
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
@RequestMapping("/api/v1/animelist")
public class UserAnimeListEntryController {

    private final UserAnimeListEntryService userAnimeListEntryService;

    public UserAnimeListEntryController(@Qualifier(value = "postgresUserAnimeListService") UserAnimeListEntryService userAnimeListEntryService) {
        this.userAnimeListEntryService = userAnimeListEntryService;
    }

    // Get all anime for a specific user (userId)
    @GetMapping("")
    public UserAnimeListEntryPagination getUserListAllAnime(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "15", required = false) Integer pageSize
    ) {
        return userAnimeListEntryService.getAllUserAnimeListEntries(pageNo, pageSize);
    }

    // Get details of a specific anime (animeId) from the user's list (userId)
    @GetMapping("/{animeId}")
    public UserAnimeListEntryDTO getUserListSpecificAnime(@PathVariable("animeId") Integer animeId) {
        return userAnimeListEntryService.getSpecificUserListAnime(animeId);
    }

    // Add a new anime entry for a user
    @PostMapping("/{animeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAnimeListEntry createUserListAnimeEntry(@PathVariable("animeId") Integer animeId, @RequestBody UserAnimeListEntry entry) {
        return userAnimeListEntryService.createUserAnimeListEntry(animeId, entry);
    }

    // Update details of an anime in the user's list
    @PutMapping("/{animeId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserAnimeListEntry(@PathVariable("animeId") Integer animeId, @RequestBody UserAnimeListEntry entry) {
        userAnimeListEntryService.updateUserAnimeListEntry(animeId, entry);
    }

    // Delete an anime from the user's list
    @DeleteMapping("/{animeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserListAnimeEntry(@PathVariable("animeId") Integer animeId) {
        userAnimeListEntryService.deleteUserAnimeListEntry(animeId);
    }

//    @GetMapping("/anime/{animeId}")
//    public List<UserDTO> getUsersWhoWatchedAnime(@PathVariable("animeId") Integer animeId) {
//        return userAnimeListEntryService.getUsersWhoWatchedAnime(animeId);
//    }
//
//    @GetMapping("/status")
//    public List<UserAnimeListEntryDTO> getUserAnimeListByStatus(@PathVariable("userId") Integer userId, @RequestBody String status) {
//        return userAnimeListEntryService.getUserAnimeListByStatus(userId, status);
//    }

}
