package com.clickclackmessenger.core.di.modules;

import android.app.Application;
import android.content.Context;

import com.clickclackmessenger.core.repositories.db_repository.remote_db.FirebaseSignInDBRepository;
import com.clickclackmessenger.core.repositories.db_repository.remote_db.SignInDBRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.UserSharedPrefRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInToFirebaseRepository;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    SharedPrefRepository provideSharedPrefRepository(Application application) {
        return new UserSharedPrefRepository(application.getSharedPreferences(UserSharedPrefRepository.SHARED_PREF_NAME, Context.MODE_PRIVATE));
    }

    @Provides
    @Singleton
    SignInDBRepository provideSignInDBRepository(SharedPrefRepository sharedPrefRepository) {
        return new FirebaseSignInDBRepository(FirebaseDatabase.getInstance().getReference(), sharedPrefRepository);
    }

    @Provides
    @Singleton
    SignInRepository provideSignInRepository(SignInDBRepository signInDBRepository) {
        return new SignInToFirebaseRepository(signInDBRepository);
    }
}
