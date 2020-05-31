package ru.itis.marshrutssite.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.marshrutssite.dto.UsersSearchResult;
import ru.itis.marshrutssite.services.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService service;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // /search/users?q=...&page=1&size=2
    @GetMapping("/users")
    public UsersSearchResult searchUsers(@RequestParam("q") String query,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("size") Integer size,
                                         @RequestParam(value = "state", required = false) String state) {
        logger.info("START SEARCH");
        return service.searchUsers(query, state, page, size);
    }
}