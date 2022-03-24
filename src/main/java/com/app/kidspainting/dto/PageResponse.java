package com.app.kidspainting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResponse<T> {
    T items;
    int index;
    long totalItems;
    int totalPage;
}