//package com.example.animetracker.repository;
//
//import com.example.animetracker.model.AnimeListStatus;
//import com.example.animetracker.model.UserAnimeList;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class InMemoryUserAnimeListRepository {
//    // Pretend this is the db
//    private static final List<UserAnimeList> db = new ArrayList<>();
//
//    // Initialize some anime lists from users
//    static {
//        db.add(new UserAnimeList(1, 1, AnimeListStatus.WATCHING));
//        db.add(new UserAnimeList(2, 1, AnimeListStatus.COMPLETED));
//        db.add(new UserAnimeList(3, 2, AnimeListStatus.PLAN_TO_WATCH));
//    }
//
//    // get all entries
//
//
//    // create an entry
//    public void createAnimeListEntry(UserAnimeList userAnimeList) {
//        db.add(userAnimeList);
//    }
//
//    // get all entries
//
//
//
//
//}
