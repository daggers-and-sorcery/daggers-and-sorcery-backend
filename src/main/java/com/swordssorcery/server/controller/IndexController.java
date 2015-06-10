package com.swordssorcery.server.controller;

import com.swordssorcery.server.model.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }
}
