package com.maybach7.formhandler.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    MALE,
    FEMALE;

    public static Optional<Gender> find(String gender) {
        return Arrays.stream(values())
                .filter(el -> el.name().equalsIgnoreCase(gender))
                .findFirst();
    }
}