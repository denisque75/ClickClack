package com.clickclackmessenger.ui.login.registration;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface RegistrationView extends MvpView {

    void registrationIsFinished();

    void incorrectName(String msg);

    void incorrectLastName(String msg);
}
