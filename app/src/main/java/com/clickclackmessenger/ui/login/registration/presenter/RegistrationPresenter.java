package com.clickclackmessenger.ui.login.registration.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.clickclackmessenger.core.callbacks.OnChangeNameCallback;
import com.clickclackmessenger.core.dto.UserName;
import com.clickclackmessenger.core.use_cases.change_name_use_case.ChangeNameUseCase;
import com.clickclackmessenger.core.use_cases.validation.Status;
import com.clickclackmessenger.ui.login.registration.RegistrationView;

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {
    private final ChangeNameUseCase changeNameUseCase;

    public RegistrationPresenter(ChangeNameUseCase changeNameUseCase) {
        this.changeNameUseCase = changeNameUseCase;
    }

    public void registerUser(String name, String lastName) {
        changeNameUseCase.changeName(new UserName(name, lastName), new OnChangeNameCallback() {
            @Override
            public void onIncorrectNaming(Status status) {
                switch (status) {
                    case EMPTY_NAME: {
                        getViewState().incorrectName("Incorrect name");
                        break;
                    }
                    case EMPTY_SURNAME: {
                        getViewState().incorrectLastName("Incorrect lastName");
                        break;
                    }
                }
            }

            @Override
            public void onSuccess() {
                getViewState().registrationIsFinished();
            }
        });
    }
}
