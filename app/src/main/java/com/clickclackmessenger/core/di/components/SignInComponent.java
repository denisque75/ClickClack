package com.clickclackmessenger.core.di.components;

import com.clickclackmessenger.core.di.scopes.PerActivity;
import com.clickclackmessenger.core.use_cases.signIn.SignInUseCase;
import com.clickclackmessenger.ui.login.sign_in.SignInFragment;
import com.clickclackmessenger.ui.login.sign_in.presenter.SignInPresenter;

import dagger.Provides;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {SignInComponent.Module.class})
public interface SignInComponent {

    void inject(SignInFragment fragment);

    @dagger.Module
    class Module {

        @Provides
        @PerActivity
        SignInPresenter provideSignInPresenter(SignInUseCase signInUseCase) {
            return new SignInPresenter(signInUseCase);
        }
    }
}
