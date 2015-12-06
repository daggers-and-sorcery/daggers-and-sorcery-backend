package com.morethanheroic.swords.news.view.controller;

import com.morethanheroic.swords.news.domain.NewsEntity;
import com.morethanheroic.swords.news.service.NewsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Access point for the news.
 */
@RestController
public class NewsController {

    private static final int NEWS_AMOUNT_PER_PAGE = 10;

    @Autowired
    private NewsFacade newsFacade;

    @ResponseBody
    @RequestMapping(value = "/news/last", method = RequestMethod.GET)
    public List<NewsEntity> news() {
        return newsFacade.getLastNewsEntity(NEWS_AMOUNT_PER_PAGE);
    }
}
