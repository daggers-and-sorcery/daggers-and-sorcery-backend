package com.swordssorcery.server.controller;

import com.swordssorcery.server.model.User;
import com.swordssorcery.server.model.repository.UserRepository;
import com.swordssorcery.server.session.SessionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public HashMap<String, String> login(HttpSession session, @RequestParam String username, @RequestParam String password) {
        User login = userRepository.queryByNameAndPass(username, password);

        HashMap<String, String> response = new HashMap<>();

        if (login != null) {
            response.put("success", "true");

            session.setAttribute(SessionType.USER, login);
        } else {
            response.put("error", "Wrong username or password!");
        }

        return response;
    }
}
