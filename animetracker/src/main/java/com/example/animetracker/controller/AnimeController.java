package com.example.animetracker.controller;

import com.example.animetracker.model.Anime;
import com.example.animetracker.service.AnimeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/anime")
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
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

    @PostMapping("")
    public void createAnime(@RequestBody Anime anime) {
        animeService.createAnime(anime);
    }

    @PutMapping("/{id}")
    public void updateAnime(@RequestBody Anime anime, @PathVariable("id") Integer id) {
        animeService.updateAnime(anime, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnime(@PathVariable("id") Integer id) {
        animeService.deleteAnime(id);
    }

}
