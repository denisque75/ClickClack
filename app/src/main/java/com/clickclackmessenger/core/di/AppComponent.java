package com.clickclackmessenger.core.di;

import com.clickclackmessenger.core.di.components.MainChatComponent;
import com.clickclackmessenger.core.di.components.RegistrationComponent;
import com.clickclackmessenger.core.di.components.SignInComponent;
import com.clickclackmessenger.core.di.modules.AppModule;
import com.clickclackmessenger.core.di.modules.FirebaseModule;
import com.clickclackmessenger.core.di.modules.RepositoryModule;
import com.clickclackmessenger.core.di.modules.UseCaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        RepositoryModule.class,
        UseCaseModule.class,
        FirebaseModule.class
})
public interface AppComponent {

    SignInComponent plus(SignInComponent.Module module);

    RegistrationComponent plus(RegistrationComponent.Module module);

    MainChatComponent plus(MainChatComponent.Module module);
}
