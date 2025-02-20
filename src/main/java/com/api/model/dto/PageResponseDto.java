package com.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponseDto<T>{

    private long totalElements;
    private int totalPages;
    private List<T> content;

    public PageResponseDto(long totalElements, int totalPages, List<T> content) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.content = content;
    }

}
