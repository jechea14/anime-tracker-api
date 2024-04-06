package com.example.animetracker.service.impl;

import com.example.animetracker.dto.UserAnimeListDTO;
import com.example.animetracker.dto.UserDTO;
import com.example.animetracker.model.Anime;
import com.example.animetracker.model.AnimeListStatus;
import com.example.animetracker.model.User;
import com.example.animetracker.model.UserAnimeList;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.UserAnimeListRepository;
import com.example.animetracker.repository.UserRepository;
import com.example.animetracker.service.UserAnimeListService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "postgresUserAnimeListService")
public class UserAnimeListServiceImpl implements UserAnimeListService {

    private final UserAnimeListRepository userAnimeListRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

    public UserAnimeListServiceImpl(UserAnimeListRepository userAnimeListRepository, UserRepository userRepository, AnimeRepository animeRepository) {
        this.userAnimeListRepository = userAnimeListRepository;
        this.animeRepository = animeRepository;
        this.userRepository = userRepository;
    }

    // Get all anime for a specific user (userId)
    @Override
    public List<UserAnimeListDTO> getUserAnimeList(Integer userId) {
        // Fetch user from user repository
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return userAnimeListRepository.findByUser(user).stream().map(userAnimeList -> new UserAnimeListDTO(
                    userAnimeList.getId(),
                    userAnimeList.getAnime(),
                    userAnimeList.getWatchStatus()
            )).toList();
        } else {
            return Collections.emptyList();
        }

    }

    // Get details of a specific anime (animeId) from the user's list (userId)
    @Override
    public Optional<UserAnimeListDTO> getSpecificUserListAnime(Integer userId, Integer animeId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isPresent() && animeOptional.isPresent()) {
            User user = userOptional.get();
            Anime anime = animeOptional.get();

            return userAnimeListRepository.findByUserAndAnime(user, anime).stream().map(userAnimeList -> new UserAnimeListDTO(
                    userAnimeList.getId(),
                    userAnimeList.getAnime(),
                    userAnimeList.getWatchStatus()
            )).findFirst();
        } else {
            return Optional.empty();
        }
    }

    // Add a new anime entry for a user
    @Override
    public UserAnimeList createUserAnimeListEntry(Integer userId, Integer animeId, String watchStatus) {
        // Fetch user and anime objects using their repositories
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        // Check if user and anime exist
        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
            throw new EntityNotFoundException("User or Anime not found");
        }

        // Create a new UserAnimeList object
        UserAnimeList userAnimeList = new UserAnimeList();
        userAnimeList.setAnime(animeOptional.get());
        userAnimeList.setWatchStatus(watchStatus);

        userAnimeList.setUser(userOptional.get());

        // Save the UserAnimeList entry
        return userAnimeListRepository.save(userAnimeList);
    }

    // Update details of an anime in the user's list
    @Override
    public void updateUserAnimeListEntry(Integer userId, Integer animeId, UserAnimeList updateData) {

    }

    // Delete an anime from the user's list
    @Override
    public void deleteUserAnimeListEntry(Integer userId, Integer animeId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (userOptional.isPresent() && animeOptional.isPresent()) {
            User user = userOptional.get();
            Anime anime = animeOptional.get();
            List<UserAnimeList> userAnimeLists = userAnimeListRepository.findByUserAndAnime(user, anime);
            userAnimeListRepository.deleteAll(userAnimeLists);
        }
    }

    @Override
    public List<UserDTO> getUsersWhoWatchedAnime(Integer animeId) {
        Optional<Anime> animeOptional = animeRepository.findById(animeId);

        if (animeOptional.isPresent()) {
            Anime anime = animeOptional.get();

            return userAnimeListRepository.findByAnime(anime).stream().map(userAnimeList -> new UserDTO(
                    userAnimeList.getId(),
                    userAnimeList.getUser().getUsername()
            )).toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<UserAnimeListDTO> getUserAnimeListByStatus(Integer userId, String status) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return userAnimeListRepository.findByUser(user).stream().map(userAnimeList -> new UserAnimeListDTO(
                    userAnimeList.getId(),
                    userAnimeList.getAnime(),
                    status
            )).toList();
        } else {
            return Collections.emptyList();
        }
    }
}
