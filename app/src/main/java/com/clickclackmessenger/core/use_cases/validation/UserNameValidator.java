package com.clickclackmessenger.core.use_cases.validation;

public class UserNameValidator implements RegisterValidator {

    @Override
    public Status isValidName(String name) {
        if (name.equals("")) {
            return Status.EMPTY_NAME;
        } else {
            return Status.VALID;
        }
    }

    @Override
    public Status isValidLastName(String lastName) {
        if (lastName.equals("")) {
            return Status.EMPTY_SURNAME;
        } else {
            return Status.VALID;
        }
    }
}
