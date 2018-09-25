package com.clickclackmessenger.core.use_cases.change_name_use_case;

import com.clickclackmessenger.core.callbacks.OnChangeNameCallback;
import com.clickclackmessenger.core.dto.UserName;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.UserRepository;
import com.clickclackmessenger.core.use_cases.validation.RegisterValidator;
import com.clickclackmessenger.core.use_cases.validation.Status;

public class ClickClackUserNameUseCase implements ChangeNameUseCase {
    private final SharedPrefRepository sharedPrefRepository;
    private final UserRepository userRepository;
    private final RegisterValidator validator;

    public ClickClackUserNameUseCase(SharedPrefRepository sharedPrefRepository, UserRepository userRepository, RegisterValidator validator) {
        this.sharedPrefRepository = sharedPrefRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public void changeName(UserName userName, OnChangeNameCallback onChangeName) {
        Status nameStatus = validator.isValidName(userName.getName());
        Status lastNameStatus = validator.isValidLastName(userName.getLastName());
        if (Status.EMPTY_NAME.equals(nameStatus)) {
            onChangeName.onIncorrectNaming(nameStatus);
        }
        if (Status.EMPTY_SURNAME.equals(lastNameStatus)) {
            onChangeName.onIncorrectNaming(lastNameStatus);
        }

        if (Status.VALID.equals(nameStatus) && Status.VALID.equals(lastNameStatus)) {
            userRepository.changeName(userName);
            sharedPrefRepository.editUserName(userName);
            onChangeName.onSuccess();
        }
    }
}
