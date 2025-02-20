package com.api.util;

import java.util.Arrays;
import java.util.List;

public class PaginationValidate {

    public static boolean isNotValidSortFieldEvent(String sortName) {
        List<String> validFields = Arrays.asList("idEvent", "name", "place","dateStart", "dateEnd", "status", "created", "ticketLimit", "ticketsSold");
        return !validFields.contains(sortName);
    }
}
