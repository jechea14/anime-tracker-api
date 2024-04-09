package com.example.animetracker.repository;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.User;
import com.example.animetracker.model.UserAnimeListEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAnimeListEntryRepository extends JpaRepository<UserAnimeListEntry, Integer> {

    List<UserAnimeListEntry> findByUser(User user);
    List<UserAnimeListEntry> findByAnime(Anime anime);
    Optional<UserAnimeListEntry> findByUserAndAnime(User user, Anime anime);
}
