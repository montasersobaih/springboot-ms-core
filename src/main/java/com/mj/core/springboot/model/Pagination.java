package com.mj.core.springboot.model;

import org.springframework.data.domain.PageRequest;

import java.util.Objects;

public final class Pagination {

    private int totalPages;

    private long totalRecords;

    private boolean hasNext;

    public Pagination() {
    }

    public Pagination(int totalPages, long totalRecords, boolean hasNext) {
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
        this.hasNext = hasNext;
    }

    public static boolean hasNextPage(int offset, int totalPages) {
        return offset + 1 < totalPages;
    }

    public static PageRequest createPageRequest(Integer offset, Integer limit) {
        return Objects.nonNull(offset) && Objects.nonNull(limit) ? PageRequest.of(offset, limit) : null;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
