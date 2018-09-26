package com.clickclackmessenger.core.repositories.search_repository;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.entities.chats.Chat;

import java.util.List;

public interface SearchRepository {

    void searchData(String query, NetworkCallback<List<Chat>> callback);
}
