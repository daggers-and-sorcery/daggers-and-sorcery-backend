package com.swordssorcery.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login() {
        System.out.println("CALLED");
        return "index";
    }
}
