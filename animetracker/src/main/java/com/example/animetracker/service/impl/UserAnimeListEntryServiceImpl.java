//package com.example.animetracker.service.impl;
//
//import com.example.animetracker.dto.AnimeDTO;
//import com.example.animetracker.dto.UserAnimeListEntryDTO;
//import com.example.animetracker.dto.UserDTO;
//import com.example.animetracker.model.Anime;
//import com.example.animetracker.model.ApplicationUser;
//import com.example.animetracker.model.UserAnimeListEntry;
//import com.example.animetracker.repository.AnimeRepository;
//import com.example.animetracker.repository.UserAnimeListEntryRepository;
//import com.example.animetracker.repository.UserRepository;
//import com.example.animetracker.service.UserAnimeListEntryService;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Qualifier(value = "postgresUserAnimeListService")
//public class UserAnimeListEntryServiceImpl implements UserAnimeListEntryService {
//
//    private final UserAnimeListEntryRepository userAnimeListEntryRepository;
//    private final AnimeRepository animeRepository;
//    private final UserRepository userRepository;
//
//    public UserAnimeListEntryServiceImpl(UserAnimeListEntryRepository userAnimeListEntryRepository, UserRepository userRepository, AnimeRepository animeRepository) {
//        this.userAnimeListEntryRepository = userAnimeListEntryRepository;
//        this.animeRepository = animeRepository;
//        this.userRepository = userRepository;
//    }
//
//    // Get all anime for a specific user (userId)
//    @Override
//    public List<UserAnimeListEntryDTO> getUserAnimeList(Integer userId) {
//        // Fetch user from user repository
//        Optional<ApplicationUser> userOptional = userRepository.findById(userId);
//
//        if (userOptional.isEmpty()) {
//            throw new EntityNotFoundException("User not found");
//        }
//
//        ApplicationUser applicationUser = userOptional.get();
//
//        return userAnimeListEntryRepository.findByUser(applicationUser).stream().map(userAnimeListEntry -> new UserAnimeListEntryDTO(
//                userAnimeListEntry.getId(),
//                new AnimeDTO(userAnimeListEntry.getAnime().getId(), userAnimeListEntry.getAnime().getTitle()),
//                userAnimeListEntry.getWatchStatus(),
//                userAnimeListEntry.getEpisodesCompleted()
//        )).toList();
//
//    }
//
//    // Get details of a specific anime (animeId) from the user's list (userId)
//    @Override
//    public UserAnimeListEntryDTO getSpecificUserListAnime(Integer userId, Integer animeId) {
//        Optional<ApplicationUser> userOptional = userRepository.findById(userId);
//        Optional<Anime> animeOptional = animeRepository.findById(animeId);
//
//        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
//            throw new EntityNotFoundException("User or Anime not found");
//        }
//
//        ApplicationUser applicationUser = userOptional.get();
//        Anime anime = animeOptional.get();
//        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByUserAndAnime(applicationUser, anime);
//        UserAnimeListEntry userAnimeListEntry = userAnimeListOptional.get();
//
//        return new UserAnimeListEntryDTO(
//                userAnimeListEntry.getId(),
//                new AnimeDTO(userAnimeListOptional.get().getAnime().getId(), userAnimeListOptional.get().getAnime().getTitle()),
//                userAnimeListOptional.get().getWatchStatus(),
//                userAnimeListOptional.get().getEpisodesCompleted()
//        );
//
////                    .stream().map(userAnimeList -> new UserAnimeListDTO(
////                    userAnimeList.getId(),
////                    userAnimeList.getAnime(),
////                    userAnimeList.getWatchStatus()
////            )).findFirst();
//
//    }
//
//    // Add a new anime entry for a user
//    @Override
//    public UserAnimeListEntry createUserAnimeListEntry(Integer userId, Integer animeId, UserAnimeListEntry entry) {
//        Integer episodes = entry.getEpisodesCompleted();
//
//        // Fetch user and anime objects using their repositories
//        Optional<ApplicationUser> userOptional = userRepository.findById(userId);
//        Optional<Anime> animeOptional = animeRepository.findById(animeId);
//
//        // Check if user and anime exist
//        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
//            throw new EntityNotFoundException("User or Anime not found");
//        }
//
//        ApplicationUser applicationUser = userOptional.get();
//        Anime anime = animeOptional.get();
//
//        if (episodes > anime.getEpisodeCount() || entry.getWatchStatus().equals("completed")) {
//            episodes = anime.getEpisodeCount();
//        }
//
//        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByUserAndAnime(applicationUser, anime);
//
//        if (userAnimeListOptional.isPresent()) {
//            throw new EntityNotFoundException("Entry already exists.");
//        }
//
//        // Create a new UserAnimeList object
//        UserAnimeListEntry userAnimeListEntry = new UserAnimeListEntry();
//        userAnimeListEntry.setAnime(animeOptional.get());
//        userAnimeListEntry.setWatchStatus(entry.getWatchStatus());
//        userAnimeListEntry.setApplicationUser(userOptional.get());
//        userAnimeListEntry.setEpisodesCompleted(episodes);
//
//        // Save the UserAnimeList entry
//        return userAnimeListEntryRepository.save(userAnimeListEntry);
//    }
//
//    // Update details of an anime in the user's list
//    @Override
//    public void updateUserAnimeListEntry(Integer userId, Integer animeId, UserAnimeListEntry entry) {
//        Optional<ApplicationUser> userOptional = userRepository.findById(userId);
//        Optional<Anime> animeOptional = animeRepository.findById(animeId);
//
//        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
//            throw new EntityNotFoundException("User or Anime not found");
//        }
//
//        ApplicationUser applicationUser = userOptional.get();
//        Anime anime = animeOptional.get();
//
//
//        Optional<UserAnimeListEntry> userAnimeListOptional = userAnimeListEntryRepository.findByUserAndAnime(applicationUser, anime);
//
//        if (userAnimeListOptional.isEmpty()) {
//            throw new EntityNotFoundException("Entry not found");
//        }
//
//        UserAnimeListEntry userAnimeListEntry = userAnimeListOptional.get();
//
//        userAnimeListEntry.setEpisodesCompleted(entry.getEpisodesCompleted());
//
//        if (entry.getEpisodesCompleted() > anime.getEpisodeCount()) {
//            userAnimeListEntry.setEpisodesCompleted(anime.getEpisodeCount());
//        }
//
//        if (entry.getEpisodesCompleted() < 0) {
//            userAnimeListEntry.setEpisodesCompleted(0);
//        }
//        userAnimeListEntry.setWatchStatus(entry.getWatchStatus());
//        userAnimeListEntryRepository.save(userAnimeListEntry);
//    }
//
//    // Delete an anime from the user's list
//    @Override
//    public void deleteUserAnimeListEntry(Integer userId, Integer animeId) {
//        Optional<ApplicationUser> userOptional = userRepository.findById(userId);
//        Optional<Anime> animeOptional = animeRepository.findById(animeId);
//
//        if (userOptional.isEmpty() || animeOptional.isEmpty()) {
//            throw new EntityNotFoundException("User or Anime not found");
//        }
//
//        ApplicationUser applicationUser = userOptional.get();
//        Anime anime = animeOptional.get();
//        Optional<UserAnimeListEntry> userAnimeListsOptional = userAnimeListEntryRepository.findByUserAndAnime(applicationUser, anime);
//
//        if (userAnimeListsOptional.isEmpty()) {
//            throw new EntityNotFoundException("Entry not found");
//        }
//
//        UserAnimeListEntry userAnimeListEntry = userAnimeListsOptional.get();
//        userAnimeListEntryRepository.delete(userAnimeListEntry);
//    }
//
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
//        return userAnimeListEntryRepository.findByUser(applicationUser).stream().map(userAnimeListEntry -> new UserAnimeListEntryDTO(
//                userAnimeListEntry.getId(),
//                new AnimeDTO(userAnimeListEntry.getAnime().getId(), userAnimeListEntry.getAnime().getTitle()),
//                status,
//                userAnimeListEntry.getEpisodesCompleted()
//        )).toList();
//
//    }
//
//    // helper method
//}
