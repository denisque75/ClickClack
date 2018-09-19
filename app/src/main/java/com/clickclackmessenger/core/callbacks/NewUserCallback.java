package com.clickclackmessenger.core.callbacks;

public interface NewUserCallback {

    /**
     * @param isNewUser returns true if this user is not contain in db else false
     */
    void newUser(boolean isNewUser);
}
