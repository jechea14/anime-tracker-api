package com.example.animetracker.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
//    private LocalDate startDate;
//    private LocalDate endDate;
//    private Double rating;
//    private Integer numOfScoringUsers;
//    private String genre;

    public Anime() {
    }



    public Anime(int i, String title) {
        this.id = i;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }
//
//    public Double getRating() {
//        return rating;
//    }
//
//    public void setRating(Double rating) {
//        this.rating = rating;
//    }
//
//    public Integer getNumOfScoringUsers() {
//        return numOfScoringUsers;
//    }
//
//    public void setNumOfScoringUsers(Integer numOfScoringUsers) {
//        this.numOfScoringUsers = numOfScoringUsers;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
}
