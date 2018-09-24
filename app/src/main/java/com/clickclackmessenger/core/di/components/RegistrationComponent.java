package com.clickclackmessenger.core.di.components;

import com.clickclackmessenger.core.di.scopes.PerActivity;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ChangeNameUseCase;
import com.clickclackmessenger.ui.login.registration.RegistrationFragment;
import com.clickclackmessenger.ui.login.registration.presenter.RegistrationPresenter;

import dagger.Provides;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {RegistrationComponent.Module.class})
public interface RegistrationComponent {

    void inject(RegistrationFragment registrationFragment);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        RegistrationPresenter provideRegistrationPresenter(ChangeNameUseCase useCase) {
            return new RegistrationPresenter(useCase);
        }

    }
}
