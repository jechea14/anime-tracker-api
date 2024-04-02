package com.example.animetracker.controller;

import com.example.animetracker.model.Anime;
import com.example.animetracker.service.AnimeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(@Qualifier(value = "postgresAnimeService") AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("")
    public List<Anime> getAllAnime() {
        return animeService.getAllAnime();
    }

    @GetMapping("/{id}")
    public Optional<Anime> getAnime(@PathVariable("id") Integer id) {
        return animeService.getAnime(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createAnime(@RequestBody Anime anime) {
        animeService.createAnime(anime);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateAnime(@RequestBody Anime anime, @PathVariable("id") Integer id) {
        animeService.updateAnime(anime, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAnime(@PathVariable("id") Integer id) {
        animeService.deleteAnimeById(id);
    }

    private URI getLocation(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}
