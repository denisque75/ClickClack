package com.clickclackmessenger.core.di.modules;

import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.UserSharedPrefRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.UserRepository;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ChangeNameUseCase;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ClickClackUserNameUseCase;
import com.clickclackmessenger.core.use_cases.signIn.ClickClackSignInUseCase;
import com.clickclackmessenger.core.use_cases.signIn.SignInUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    SignInUseCase provideSignInUseCase(SignInRepository signInRepository, SharedPrefRepository sharedPrefRepository) {
        return new ClickClackSignInUseCase(signInRepository, sharedPrefRepository);
    }

    @Provides
    @Singleton
    ChangeNameUseCase provideChangeNameUseCase(UserSharedPrefRepository sharedPrefRepository, UserRepository userRepository) {
        return new ClickClackUserNameUseCase(sharedPrefRepository, userRepository);
    }
}
