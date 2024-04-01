package com.example.animetracker.Config;

import com.example.animetracker.model.Anime;
import com.example.animetracker.repository.AnimeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AnimeConfig {

    @Bean
    CommandLineRunner commandLineRunner(AnimeRepository animeRepository) {
        return args -> {
            Anime naruto = new Anime(1,
                    "Naruto"
            );
            Anime bleach = new Anime(2,
                    "Bleach"
            );

            Anime JJK = new Anime(3,
                    "JJK"
            );

            animeRepository.saveAll(List.of(naruto, bleach, JJK));
        };
    }
}
