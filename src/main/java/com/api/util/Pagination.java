package com.api.util;

import com.api.exception.ApiException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

public class Pagination {

    public static Pageable createSimplePagination(int page, int size, String sort, String sortName) {
        try {
            return PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sort), sortName));
        } catch (Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Params not valid");
        }
    }
}
