package com.clickclackmessenger.core.repositories.db_repository.shared_pref;

import com.clickclackmessenger.core.dto.UserName;
import com.clickclackmessenger.core.entities.users.BaseUser;

public interface SharedPrefRepository {

    void addUser(BaseUser baseUser);

    void removeUser();

    BaseUser readUser();

    void editUserName(UserName userName);
}
