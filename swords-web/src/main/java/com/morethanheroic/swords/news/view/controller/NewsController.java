package com.morethanheroic.swords.news.view.controller;

import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.service.NewsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Access point for the news.
 */
@RestController
@RequiredArgsConstructor
public class NewsController {

    private static final int NEWS_AMOUNT_PER_PAGE = 10;

    private final NewsFacade newsFacade;

    @ResponseBody
    @GetMapping(value = "/news/last")
    public List<NewsEntity> news() {
        //TODO: Use a builder instead of this! Send the date normally because now LocalDate is formatted badly.
        return newsFacade.getLastNewsEntity(NEWS_AMOUNT_PER_PAGE);
    }
}
