package com.clickclackmessenger.core.callbacks;

import com.clickclackmessenger.core.use_cases.validation.Status;

public interface OnChangeNameCallback {

    void onIncorrectNaming(Status status);

    void onSuccess();
}
