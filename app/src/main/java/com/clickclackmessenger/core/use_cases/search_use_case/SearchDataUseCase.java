package com.clickclackmessenger.core.use_cases.search_use_case;

import com.clickclackmessenger.core.callbacks.NetworkCallback;
import com.clickclackmessenger.core.entities.chats.Chat;
import com.clickclackmessenger.core.repositories.search_repository.SearchRepository;

import java.util.List;

public class SearchDataUseCase implements SearchUseCase {
    private final SearchRepository searchRepository;

    public SearchDataUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public void searchData(String query, NetworkCallback<List<Chat>> callback) {
        searchRepository.searchData(query, callback);
    }
}
