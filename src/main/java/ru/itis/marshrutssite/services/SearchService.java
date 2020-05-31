package ru.itis.marshrutssite.services;

import ru.itis.marshrutssite.dto.UsersSearchResult;

public interface SearchService {
    UsersSearchResult searchUsers(String query, String state, Integer page, Integer size);
}
