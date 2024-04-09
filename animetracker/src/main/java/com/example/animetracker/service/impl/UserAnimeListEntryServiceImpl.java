package com.example.animetracker.service.impl;

import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.dto.UserDTO;
import com.example.animetracker.model.Anime;
import com.example.animetracker.model.User;
import com.example.animetracker.model.UserAnimeListEntry;
import com.example.animetracker.model.WatchStatus;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.UserAnimeListEntryRepository;
import com.example.animetracker.repository.UserRepository;
import com.example.animetracker.service.UserAnimeListEntryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Override
    public List<UserAnimeListEntryDTO> getUserAnimeList(Integer userId) {
        // Fetch user from user repository
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        User user = userOptional.get();

        return userAnimeListEntryRepository.findByUser(user).stream().map(userAnimeListEntry -> new UserAnimeListEntryDTO(
                userAnimeListEntry.getId(),
                userAnimeListEntry.getAnime(),
                userAnimeListEntry.getWatchStatus()
        )).toList();

    }

    // Get details of a specific anime (animeId) from the user's list (userId)
    @Override
    public UserAnimeListEntryDTO getSpecificUserListAnime(Integer userId, Integer animeId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        User user = userOptional.get();
        Anime anime = animeOptional.get();
        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByUserAndAnime(user, anime);
        return new UserAnimeListEntryDTO(userAnimeListOptional.get().getId(), userAnimeListOptional.get().getAnime(), userAnimeListOptional.get().getWatchStatus());

//                    .stream().map(userAnimeList -> new UserAnimeListDTO(
//                    userAnimeList.getId(),
//                    userAnimeList.getAnime(),
//                    userAnimeList.getWatchStatus()
//            )).findFirst();

    }

    // Add a new anime entry for a user
    @Override
    public UserAnimeListEntry createUserAnimeListEntry(Integer userId, Integer animeId, WatchStatus watchStatus) {
        // Fetch user and anime objects using their repositories
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        // Check if user and anime exist
        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        User user = userOptional.get();
        Anime anime = animeOptional.get();

        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByUserAndAnime(user, anime);

        if (userAnimeListOptional.isPresent()) {
            throw new EntityNotFoundException("Entry already exists.");
        }
        // Create a new UserAnimeList object
        UserAnimeListEntry userAnimeListEntry = new UserAnimeListEntry();
        userAnimeListEntry.setAnime(animeOptional.get());
        userAnimeListEntry.setWatchStatus(watchStatus);
        userAnimeListEntry.setUser(userOptional.get());

        // Save the UserAnimeList entry
        return userAnimeListEntryRepository.save(userAnimeListEntry);
    }

    // Update details of an anime in the user's list
    @Override
    public void updateUserAnimeListEntry(Integer userId, Integer animeId, WatchStatus status) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        User user = userOptional.get();
        Anime anime = animeOptional.get();

        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByUserAndAnime(user, anime);

        if (userAnimeListOptional.isEmpty()) {
            throw new EntityNotFoundException("Entry not found");
        }

        UserAnimeListEntry userAnimeListEntry = userAnimeListOptional.get();
        userAnimeListEntry.setWatchStatus(status);
        userAnimeListEntryRepository.save(userAnimeListEntry);
    }

    // Delete an anime from the user's list
    @Override
    public void deleteUserAnimeListEntry(Integer userId, Integer animeId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        User user = userOptional.get();
        Anime anime = animeOptional.get();
        Optional<UserAnimeListEntry> userAnimeListsOptional = userAnimeListEntryRepository.findByUserAndAnime(user, anime);

        if (userAnimeListsOptional.isEmpty()) {
            throw new EntityNotFoundException("Entry not found");
        }

        UserAnimeListEntry userAnimeListEntry = userAnimeListsOptional.get();
        userAnimeListEntryRepository.delete(userAnimeListEntry);
    }

    @Override
    public List<UserDTO> getUsersWhoWatchedAnime(Integer animeId) {
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (animeOptional.isEmpty()) {
            throw new EntityNotFoundException("Anime not found");
        }

        Anime anime = animeOptional.get();

        return userAnimeListEntryRepository.findByAnime(anime).stream().map(userAnimeListEntry -> new UserDTO(
                userAnimeListEntry.getId(),
                userAnimeListEntry.getUser().getUsername()
        )).toList();
    }

    @Override
    public List<UserAnimeListEntryDTO> getUserAnimeListByStatus(Integer userId, WatchStatus status) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        User user = userOptional.get();

        return userAnimeListEntryRepository.findByUser(user).stream().map(userAnimeListEntry -> new UserAnimeListEntryDTO(
                userAnimeListEntry.getId(),
                userAnimeListEntry.getAnime(),
                status
        )).toList();

    }

    // helper method
}
