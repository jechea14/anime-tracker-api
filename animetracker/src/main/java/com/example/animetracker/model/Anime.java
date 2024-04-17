package com.example.animetracker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer episodeCount;


    @ManyToMany(cascade = CascadeType.PERSIST)  // Optional for persisting changes to genres
    @JoinTable(name = "anime_genre",  // Name of the join table
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;


//    private LocalDate startDate;
//    private LocalDate endDate;
//    private Double rating;
//    private Integer numOfScoringUsers;


}
