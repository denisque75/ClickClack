package com.clickclackmessenger.core.use_cases.change_name_use_case;

import com.clickclackmessenger.core.dto.UserName;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.UserRepository;

public class ClickClackUserNameUseCase implements ChangeNameUseCase {
    private final SharedPrefRepository sharedPrefRepository;
    private final UserRepository userRepository;

    public ClickClackUserNameUseCase(SharedPrefRepository sharedPrefRepository, UserRepository userRepository) {
        this.sharedPrefRepository = sharedPrefRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void changeName(UserName userName) {
        userRepository.changeName(userName);
        sharedPrefRepository.editUserName(userName);
    }
}
