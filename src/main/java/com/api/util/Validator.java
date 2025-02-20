package com.api.util;

public class Validator {

    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }
}
