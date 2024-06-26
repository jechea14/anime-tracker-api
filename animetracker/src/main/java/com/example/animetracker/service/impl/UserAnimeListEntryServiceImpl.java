package com.example.animetracker.service.impl;

import com.example.animetracker.dto.AnimeDTO;
import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.dto.UserAnimeListEntryPagination;
import com.example.animetracker.dto.UserDTO;
import com.example.animetracker.model.Anime;
import com.example.animetracker.model.ApplicationUser;
import com.example.animetracker.model.UserAnimeListEntry;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.UserAnimeListEntryRepository;
import com.example.animetracker.repository.UserRepository;
import com.example.animetracker.service.UserAnimeListEntryService;
import com.example.animetracker.utils.SecurityUtility;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Qualifier(value = "postgresUserAnimeListService")
public class UserAnimeListEntryServiceImpl implements UserAnimeListEntryService {

    private final UserAnimeListEntryRepository userAnimeListEntryRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

    public UserAnimeListEntryServiceImpl(UserAnimeListEntryRepository userAnimeListEntryRepository, UserRepository userRepository, AnimeRepository animeRepository) {
        this.userAnimeListEntryRepository = userAnimeListEntryRepository;
        this.animeRepository = animeRepository;
        this.userRepository = userRepository;
    }

    // Get all anime for a specific user (userId)
//    @Override
//    public List<UserAnimeListEntryDTO> getUserAnimeList() {
//
//        String username = SecurityUtility.getSessionUser();
//        // Fetch user from user repository
//        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username);
//
//        if (userOptional.isEmpty()) {
//            throw new EntityNotFoundException("User not found");
//        }
//
//        ApplicationUser applicationUser = userOptional.get();
//
//        return userAnimeListEntryRepository.findByApplicationUser(applicationUser).stream().map(userAnimeListEntry -> new UserAnimeListEntryDTO(
//                userAnimeListEntry.getId(),
//                new AnimeDTO(userAnimeListEntry.getAnime().getTitle()),
//                userAnimeListEntry.getWatchStatus(),
//                userAnimeListEntry.getEpisodesCompleted()
//        )).toList();
//
//    }

    // Get all user anime list entries with pagination
    @Override
    public UserAnimeListEntryPagination getAllUserAnimeListEntries(Integer pageNo, Integer pageSize) {

        String username = SecurityUtility.getSessionUser();
        // Fetch user from user repository
        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        ApplicationUser applicationUser = userOptional.get();

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<UserAnimeListEntry> userAnimeListEntries = userAnimeListEntryRepository.findByApplicationUser(applicationUser, pageable);

        if (userAnimeListEntries.isEmpty()) {
            return new UserAnimeListEntryPagination();
        }

        List<UserAnimeListEntry> listOfEntries = userAnimeListEntries.getContent();
        List<UserAnimeListEntryDTO> content = listOfEntries.stream().map(userAnimeListEntry -> new UserAnimeListEntryDTO(
                userAnimeListEntry.getId(),
                new AnimeDTO(userAnimeListEntry.getAnime().getTitle()),
                userAnimeListEntry.getWatchStatus(),
                userAnimeListEntry.getEpisodesCompleted())
        ).toList();

        UserAnimeListEntryPagination userAnimeListEntryPagination = new UserAnimeListEntryPagination();
        userAnimeListEntryPagination.setContent(content);
        userAnimeListEntryPagination.setPageNo(userAnimeListEntries.getNumber());
        userAnimeListEntryPagination.setPageSize(userAnimeListEntries.getSize());
        userAnimeListEntryPagination.setTotalElements(userAnimeListEntries.getTotalElements());
        userAnimeListEntryPagination.setTotalPages(userAnimeListEntries.getTotalPages());
        userAnimeListEntryPagination.setLast(userAnimeListEntries.isLast());
        return userAnimeListEntryPagination;

    }

    // Get details of a specific anime (animeId) from the user's list (userId)
    @Override
    public UserAnimeListEntryDTO getSpecificUserListAnime(Integer animeId) {
        String username = SecurityUtility.getSessionUser();
        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        ApplicationUser applicationUser = userOptional.get();
        Anime anime = animeOptional.get();
        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByApplicationUserAndAnime(applicationUser, anime);
        UserAnimeListEntry userAnimeListEntry = userAnimeListOptional.get();

        return new UserAnimeListEntryDTO(
                userAnimeListEntry.getId(),
                new AnimeDTO(userAnimeListOptional.get().getAnime().getTitle()),
                userAnimeListOptional.get().getWatchStatus(),
                userAnimeListOptional.get().getEpisodesCompleted()
        );

//                    .stream().map(userAnimeList -> new UserAnimeListDTO(
//                    userAnimeList.getId(),
//                    userAnimeList.getAnime(),
//                    userAnimeList.getWatchStatus()
//            )).findFirst();

    }

    // Add a new anime entry for a user
    @Override
    public UserAnimeListEntry createUserAnimeListEntry(Integer animeId, UserAnimeListEntry entry) {
        String username = SecurityUtility.getSessionUser();
        Integer episodes = entry.getEpisodesCompleted();

        // Fetch user and anime objects using their repositories
        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        // Check if user and anime exist
        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        ApplicationUser applicationUser = userOptional.get();
        Anime anime = animeOptional.get();

        if (episodes > anime.getEpisodeCount() || entry.getWatchStatus().equals("completed")) {
            episodes = anime.getEpisodeCount();
        }

        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByApplicationUserAndAnime(applicationUser, anime);

        if (userAnimeListOptional.isPresent()) {
            throw new EntityNotFoundException("Entry already exists.");
        }

        // Create a new UserAnimeList object
        UserAnimeListEntry userAnimeListEntry = new UserAnimeListEntry();
        userAnimeListEntry.setAnime(animeOptional.get());
        userAnimeListEntry.setWatchStatus(entry.getWatchStatus());
        userAnimeListEntry.setApplicationUser(userOptional.get());
        userAnimeListEntry.setEpisodesCompleted(episodes);

        // Save the UserAnimeList entry
        return userAnimeListEntryRepository.save(userAnimeListEntry);
    }

    // Update details of an anime in the user's list
    @Override
    public UserAnimeListEntryDTO updateUserAnimeListEntry(Integer animeId, UserAnimeListEntry entry) {
        String username = SecurityUtility.getSessionUser();

        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        ApplicationUser applicationUser = userOptional.get();
        Anime anime = animeOptional.get();


        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByApplicationUserAndAnime(applicationUser, anime);

        if (userAnimeListOptional.isEmpty()) {
            throw new EntityNotFoundException("Entry not found");
        }

        UserAnimeListEntry userAnimeListEntry = userAnimeListOptional.get();

        userAnimeListEntry.setEpisodesCompleted(entry.getEpisodesCompleted());

        if (entry.getEpisodesCompleted() > anime.getEpisodeCount()) {
            userAnimeListEntry.setEpisodesCompleted(anime.getEpisodeCount());
        }

        if (entry.getEpisodesCompleted() < 0) {
            userAnimeListEntry.setEpisodesCompleted(0);
        }
        userAnimeListEntry.setWatchStatus(entry.getWatchStatus());
        userAnimeListEntryRepository.save(userAnimeListEntry);
        return new UserAnimeListEntryDTO(userAnimeListEntry.getId(), new AnimeDTO(userAnimeListEntry.getAnime().getTitle()), userAnimeListEntry.getWatchStatus(), userAnimeListEntry.getEpisodesCompleted());
    }

    // Delete an anime from the user's list
    @Override
    public void deleteUserAnimeListEntry(Integer animeId) {
        String username = SecurityUtility.getSessionUser();

        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        ApplicationUser applicationUser = userOptional.get();
        Anime anime = animeOptional.get();
        Optional<UserAnimeListEntry> userAnimeListsOptional = userAnimeListEntryRepository.findByApplicationUserAndAnime(applicationUser, anime);

        if (userAnimeListsOptional.isEmpty()) {
            throw new EntityNotFoundException("Entry not found");
        }

        UserAnimeListEntry userAnimeListEntry = userAnimeListsOptional.get();
        userAnimeListEntryRepository.delete(userAnimeListEntry);
    }

//    @Override
//    public List<UserDTO> getUsersWhoWatchedAnime(Integer animeId) {
//        Optional<Anime> animeOptional = animeRepository.findById(animeId);
//
//        if (animeOptional.isEmpty()) {
//            throw new EntityNotFoundException("Anime not found");
//        }
//
//        Anime anime = animeOptional.get();
//
//        return userAnimeListEntryRepository.findByAnime(anime).stream().map(userAnimeListEntry -> new UserDTO(
//                userAnimeListEntry.getId(),
//                userAnimeListEntry.getApplicationUser().getUsername()
//        )).toList();
//    }
//
//    @Override
//    public List<UserAnimeListEntryDTO> getUserAnimeListByStatus(Integer userId, String status) {
//        Optional<ApplicationUser> userOptional = userRepository.findById(userId);
//
//        if (userOptional.isEmpty()) {
//            throw new EntityNotFoundException("User not found");
//        }
//
//        ApplicationUser applicationUser = userOptional.get();
//
//        return userAnimeListEntryRepository.findByApplicationUser(applicationUser).stream().map(userAnimeListEntry -> new UserAnimeListEntryDTO(
//                userAnimeListEntry.getId(),
//                new AnimeDTO(userAnimeListEntry.getAnime().getId(), userAnimeListEntry.getAnime().getTitle()),
//                status,
//                userAnimeListEntry.getEpisodesCompleted()
//        )).toList();
//    }

    // helper method
}
