package com.clickclackmessenger.core.di.components;

import com.clickclackmessenger.core.di.scopes.PerActivity;
import com.clickclackmessenger.core.use_cases.chats.ChatUseCase;
import com.clickclackmessenger.ui.inherit_chat.InheritChatActivity;
import com.clickclackmessenger.ui.inherit_chat.presenter.InheritChatPresenter;

import dagger.Provides;
import dagger.Subcomponent;

@Subcomponent(modules = {InheritChatComponent.Module.class})
@PerActivity
public interface InheritChatComponent {
    void inject(InheritChatActivity activity);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        InheritChatPresenter providesInheritChatPresenter(ChatUseCase chatUseCase) {
            return new InheritChatPresenter(chatUseCase);
        }
    }
}
