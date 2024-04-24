package com.example.animetracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAnimeListEntryPagination {
    private List<UserAnimeListEntryDTO> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;

}
