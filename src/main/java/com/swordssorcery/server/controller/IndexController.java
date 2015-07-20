package com.swordssorcery.server.controller;

import com.swordssorcery.server.model.db.news.NewsDatabaseEntity;
import com.swordssorcery.server.model.db.news.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }

    @ResponseBody
    @RequestMapping(value = "/news/last", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<NewsDatabaseEntity> news() {
        return newsRepository.findLast(5);
    }
}
