package com.example.animetracker.service;

import com.example.animetracker.dto.AnimeDTO;
import com.example.animetracker.dto.UserAnimeListEntryDTO;
import com.example.animetracker.dto.UserAnimeListEntryPagination;
import com.example.animetracker.model.Anime;
import com.example.animetracker.model.ApplicationUser;
import com.example.animetracker.model.Role;
import com.example.animetracker.model.UserAnimeListEntry;
import com.example.animetracker.repository.AnimeRepository;
import com.example.animetracker.repository.UserAnimeListEntryRepository;
import com.example.animetracker.repository.UserRepository;
import com.example.animetracker.service.impl.UserAnimeListEntryServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAnimeLisEntryServiceTests {

    @Mock
    private UserAnimeListEntryRepository userAnimeListEntryRepository;

    @Mock
    private AnimeRepository animeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAnimeListEntryServiceImpl userAnimeListEntryService;

    private Anime anime;
    private UserAnimeListEntry userAnimeListEntry;
    private AnimeDTO animeDTO;
    private UserAnimeListEntryDTO userAnimeListEntryDTO;
    private Role role;
    private ApplicationUser user;

    @BeforeEach
    public void init() {
        anime = Anime.builder().id(1).title("Naruto").episodeCount(500).build();
        animeDTO = AnimeDTO.builder().title("Naruto").build();
        role = Role.builder().roleId(1).authority("ADMIN").build();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user = ApplicationUser.builder().userId(1).username("admin").password("password").authorities(roles).build();

        userAnimeListEntry = UserAnimeListEntry.builder()
                .applicationUser(user)
                .anime(anime)
                .watchStatus("completed")
                .episodesCompleted(500)
                .build();

        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);
    }

    @Test
    public void UserAnimeListService_CreateUserAnimeList_ReturnsUserAnimeListEntry() {
        // Set up mock authentication
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        when(mockAuthentication.getName()).thenReturn("admin");
        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);

        when(animeRepository.findById(anime.getId())).thenReturn(Optional.ofNullable(anime));
        when(userRepository.findByUsername("admin")).thenReturn(Optional.ofNullable(user));

        when(userAnimeListEntryRepository.save(any(UserAnimeListEntry.class))).thenReturn(userAnimeListEntry);

        UserAnimeListEntry savedEntry = userAnimeListEntryService.createUserAnimeListEntry(anime.getId(), userAnimeListEntry);

        Assertions.assertThat(savedEntry).isNotNull();

        SecurityContextHolder.clearContext();
    }

    @Test
    public void UserAnimeListService_GetAllUserAnimeListEntries_ReturnsAllEntries() {
        // Mock repositories
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        // Mock user anime list entries
        List<UserAnimeListEntry> userAnimeListEntries = List.of(userAnimeListEntry);
        Page<UserAnimeListEntry> page = new PageImpl<>(userAnimeListEntries);
        when(userAnimeListEntryRepository.findByApplicationUser(any(ApplicationUser.class), any(Pageable.class))).thenReturn(page);

        // Set up mock authentication
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        when(mockAuthentication.getName()).thenReturn("admin");
        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);

        // Call service method
        UserAnimeListEntryPagination result = userAnimeListEntryService.getAllUserAnimeListEntries(0, 10);

        // Assertions
        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void UserAnimeListService_GetOneUserAnimeListEntry_ReturnsOneUserAnimeListEntry() {
        // Set up mock authentication
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        when(mockAuthentication.getName()).thenReturn("admin");
        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);

        when(animeRepository.findById(anime.getId())).thenReturn(Optional.ofNullable(anime));
        when(userRepository.findByUsername("admin")).thenReturn(Optional.ofNullable(user));
        when(userAnimeListEntryRepository.findByApplicationUserAndAnime(user, anime)).thenReturn(Optional.ofNullable(userAnimeListEntry));

        UserAnimeListEntryDTO returnedAnimeList = userAnimeListEntryService.getSpecificUserListAnime(anime.getId());

        // assert
        Assertions.assertThat(returnedAnimeList).isNotNull();
    }

    @Test
    public void UserAnimeListService_UpdateUserAnimeListEntry_ReturnsUserAnimeListEntry() {
        // Set up mock authentication
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        when(mockAuthentication.getName()).thenReturn("admin");
        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);

        when(animeRepository.findById(anime.getId())).thenReturn(Optional.ofNullable(anime));
        when(userRepository.findByUsername("admin")).thenReturn(Optional.ofNullable(user));
        when(userAnimeListEntryRepository.findByApplicationUserAndAnime(user, anime)).thenReturn(Optional.ofNullable(userAnimeListEntry));

        UserAnimeListEntryDTO returnedEntry = userAnimeListEntryService.updateUserAnimeListEntry(anime.getId(), userAnimeListEntry);

        Assertions.assertThat(returnedEntry).isNotNull();
    }

    @Test
    public void UserAnimeListService_DeleteUserAnimeListEntry_Returns() {
        // Set up mock authentication
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        when(mockAuthentication.getName()).thenReturn("admin");
        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);

        when(animeRepository.findById(anime.getId())).thenReturn(Optional.ofNullable(anime));
        when(userRepository.findByUsername("admin")).thenReturn(Optional.ofNullable(user));
        when(userAnimeListEntryRepository.findByApplicationUserAndAnime(user, anime)).thenReturn(Optional.ofNullable(userAnimeListEntry));

        assertAll(() -> userAnimeListEntryService.deleteUserAnimeListEntry(anime.getId()));

    }
}
