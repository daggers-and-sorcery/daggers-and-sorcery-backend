package com.morethanheroic.swords.index.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Show the index page.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }
}
