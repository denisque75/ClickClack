package com.clickclackmessenger.core.di.modules;

import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
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
}
