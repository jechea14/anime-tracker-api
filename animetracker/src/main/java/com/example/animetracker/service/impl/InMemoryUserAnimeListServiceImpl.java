//package com.example.animetracker.service.impl;
//
//import com.example.animetracker.model.Anime;
//import com.example.animetracker.model.UserAnimeList;
//import com.example.animetracker.repository.InMemoryAnimeRepository;
//import com.example.animetracker.service.UserAnimeListService;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class InMemoryUserAnimeListServiceImpl implements UserAnimeListService {
//    private final InMemoryAnimeRepository inMemoryAnimeRepository;
//
//    public InMemoryUserAnimeListServiceImpl(InMemoryAnimeRepository inMemoryAnimeRepository) {
//        this.inMemoryAnimeRepository = inMemoryAnimeRepository;
//    }
//
//    @Override
//    public Optional<UserAnimeList> getUserListAllAnime(Integer userId) {
//        return null;
//    }
//
//    @Override
//    public Optional<Anime> getUserListAnime(Integer userId, Integer animeId) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void createAnimeEntry(Integer userId, Anime anime) {
//
//    }
//
//    @Override
//    public void updateAnimeEntry(Integer userId, Integer animeId) {
//
//    }
//
//    @Override
//    public void deleteAnimeEntry(Integer userId, Integer animeId) {
//
//    }
//}
