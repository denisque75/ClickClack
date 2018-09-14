package com.clickclackmessenger.core.callbak;

public interface NetworkCallback<T> {

    void onSuccess(T t);

    void onFailure(Exception ex);
}
