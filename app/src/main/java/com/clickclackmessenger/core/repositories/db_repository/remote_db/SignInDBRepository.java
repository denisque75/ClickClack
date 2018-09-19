package com.clickclackmessenger.core.repositories.db_repository.remote_db;

import com.clickclackmessenger.core.callbacks.NewUserCallback;

public interface SignInDBRepository {

    /**
     * @param userCallback returns true if user will be created. False if user consists in db.
     */
    void saveUserToDB(NewUserCallback userCallback);
}
