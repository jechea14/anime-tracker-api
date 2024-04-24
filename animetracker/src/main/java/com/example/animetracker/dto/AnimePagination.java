package com.example.animetracker.dto;

import com.example.animetracker.model.Anime;
import lombok.Data;

import java.util.List;

@Data
public class AnimePagination {
    private List<Anime> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;

}
