package com.clickclackmessenger.core.callbacks;

public interface NetworkCallback<T> {

    void onSuccess(T t);

    void onFailure(Exception ex);
}
