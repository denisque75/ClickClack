package com.clickclackmessenger.core.di.components;

import com.clickclackmessenger.core.di.scopes.PerActivity;
import com.clickclackmessenger.core.use_cases.search_use_case.SearchUseCase;
import com.clickclackmessenger.ui.chat_screens.ChatMainScreenFragment;
import com.clickclackmessenger.ui.chat_screens.presenter.ChatScreenPresenter;

import dagger.Provides;
import dagger.Subcomponent;

@Subcomponent(modules = {MainChatComponent.Module.class})
@PerActivity
public interface MainChatComponent {
    void inject(ChatMainScreenFragment fragment);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        ChatScreenPresenter provideChatScreenPresenter(SearchUseCase searchUseCase) {
            return new ChatScreenPresenter(searchUseCase);
        }
    }
}
