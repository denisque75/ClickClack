package com.clickclackmessenger.core.repositories.user_remote_repository;

import com.clickclackmessenger.core.dto.UserName;

public interface UserRepository {

    void changeName(UserName userName);
}
