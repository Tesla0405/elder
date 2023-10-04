package com.qxy.elder.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class PageResult<T> {
    private Long total;
    private List<T> data;

    private static PageResult EMPTY_PAGE = PageResult.builder()
            .total(0L)
            .data(Collections.emptyList())
            .build();

    public static PageResult emptyPage() {
        return EMPTY_PAGE;
    }
}
