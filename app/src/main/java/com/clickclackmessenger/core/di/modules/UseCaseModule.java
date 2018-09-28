package com.clickclackmessenger.core.di.modules;

import com.clickclackmessenger.core.repositories.chat_repository.ChatRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.SharedPrefRepository;
import com.clickclackmessenger.core.repositories.db_repository.shared_pref.UserSharedPrefRepository;
import com.clickclackmessenger.core.repositories.search_repository.SearchRepository;
import com.clickclackmessenger.core.repositories.sign_in.SignInRepository;
import com.clickclackmessenger.core.repositories.user_remote_repository.UserRepository;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ChangeNameUseCase;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ClickClackUserNameUseCase;
import com.clickclackmessenger.core.use_cases.chats.ChatUseCase;
import com.clickclackmessenger.core.use_cases.chats.InheritChatUseCase;
import com.clickclackmessenger.core.use_cases.search_use_case.SearchDataUseCase;
import com.clickclackmessenger.core.use_cases.search_use_case.SearchUseCase;
import com.clickclackmessenger.core.use_cases.signIn.ClickClackSignInUseCase;
import com.clickclackmessenger.core.use_cases.signIn.SignInUseCase;
import com.clickclackmessenger.core.use_cases.validation.RegisterValidator;
import com.clickclackmessenger.core.use_cases.validation.UserNameValidator;

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
    RegisterValidator provideRegisterValidator() {
        return new UserNameValidator();
    }

    @Provides
    @Singleton
    ChangeNameUseCase provideChangeNameUseCase(UserSharedPrefRepository sharedPrefRepository, UserRepository userRepository, RegisterValidator validator) {
        return new ClickClackUserNameUseCase(sharedPrefRepository, userRepository, validator);
    }

    @Provides
    @Singleton
    SearchUseCase provideSearchUseCase(SearchRepository repository) {
        return new SearchDataUseCase(repository);
    }

    @Provides
    @Singleton
    ChatUseCase provideChatUseCase(ChatRepository chatRepository) {
        return new InheritChatUseCase(chatRepository);
    }
}
