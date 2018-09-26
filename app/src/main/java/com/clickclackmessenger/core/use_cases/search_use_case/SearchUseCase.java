package com.clickclackmessenger.core.use_cases.search_use_case;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.entities.chats.Chat;

import java.util.List;

public interface SearchUseCase {

    void searchData(String query, NetworkCallback<List<Chat>> callback);
}
