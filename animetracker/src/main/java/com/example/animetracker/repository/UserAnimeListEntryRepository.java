package com.example.animetracker.repository;

import com.example.animetracker.model.Anime;
import com.example.animetracker.model.ApplicationUser;
import com.example.animetracker.model.UserAnimeListEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnimeListEntryRepository extends JpaRepository<UserAnimeListEntry, Integer> {
    List<UserAnimeListEntry> findByApplicationUser(ApplicationUser user);
    List<UserAnimeListEntry> findByAnime(Anime anime);
    Optional<UserAnimeListEntry> findByApplicationUserAndAnime(ApplicationUser user, Anime anime);
}
