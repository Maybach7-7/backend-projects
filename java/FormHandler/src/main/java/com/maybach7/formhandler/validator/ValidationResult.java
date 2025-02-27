package com.maybach7.formhandler.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    private List<Error> errors = new ArrayList<>();

    public void add(Error error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
