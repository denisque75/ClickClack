package com.clickclackmessenger.core.use_cases.validation;

public interface RegisterValidator {

    Status isValidName(String name);

    Status isValidLastName(String lastName);
}
