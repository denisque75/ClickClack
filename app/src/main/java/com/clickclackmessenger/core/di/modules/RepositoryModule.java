package com.clickclackmessenger.core.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.clickclackmessenger.core.repositories.chat_repository.ChatRepository;
import com.clickclackmessenger.core.repositories.chat_repository.InheritChatRepository;
import com.clickclackmessenger.core.repositories.db_repository.remote_db.FirebaseSignInDBRepository;
import com.clickclackmessenger.core.repositories.db_repository.remote_db.SignInDBRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.UserSharedPrefRepository;
import com.clickclackmessenger.core.repositories.search_repository.SearchInFirebaseRepository;
import com.clickclackmessenger.core.repositories.search_repository.SearchRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInToFirebaseRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.FirebaseUserRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.UserRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences(UserSharedPrefRepository.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPrefRepository provideSharedPrefRepository(SharedPreferences sharedPreferences) {
        return new UserSharedPrefRepository(sharedPreferences);
    }

    @Provides
    @Singleton
    SignInDBRepository provideSignInDBRepository(DatabaseReference database, SharedPrefRepository sharedPrefRepository) {
        return new FirebaseSignInDBRepository(database, sharedPrefRepository);
    }

    @Provides
    @Singleton
    SignInRepository provideSignInRepository(SignInDBRepository signInDBRepository) {
        return new SignInToFirebaseRepository(signInDBRepository);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository() {
        return new FirebaseUserRepository(FirebaseDatabase.getInstance().getReference());
    }

    @Provides
    @Singleton
    SearchRepository providesSearchRepository(DatabaseReference databaseReference) {
        return new SearchInFirebaseRepository(databaseReference);
    }

    @Provides
    @Singleton
    ChatRepository providesChatRepository(DatabaseReference databaseReference) {
        return new InheritChatRepository(databaseReference);
    }
}
