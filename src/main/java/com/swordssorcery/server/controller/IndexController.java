package com.swordssorcery.server.controller;

import com.swordssorcery.server.model.News;
import com.swordssorcery.server.model.repository.NewsRepository;
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

    @ResponseBody
    @RequestMapping(value = "/news/last", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<News> news() {
        return newsRepository.findLast(5);
    }
}
