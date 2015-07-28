package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserRepository;
import com.morethanheroic.swords.common.session.SessionAttributeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

@RestController
public class UserLoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public HashMap<String, String> login(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException {
        UserDatabaseEntity user = userRepository.findByUsernameAndPassword(username, shaPasswordEncoder.encodePassword(password, null));

        HashMap<String, String> response = new HashMap<>();

        if (user != null) {
            response.put("success", "true");

            session.setAttribute(SessionAttributeType.USER_ID, user.getId());

            user.setLastLoginDate(new Date());
            userRepository.save(user);
        } else {
            response.put("success", "false");
            response.put("error", "Wrong username or password!");
        }

        return response;
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public HashMap<String, String> info(HttpSession session) {
        HashMap<String, String> response = new HashMap<>();

        response.put("loggedIn", String.valueOf(session.getAttribute(SessionAttributeType.USER_ID) != null));

        return response;
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public HashMap<String, String> logout(HttpSession session) {
        session.invalidate();

        HashMap<String, String> response = new HashMap<>();

        response.put("loggedIn", "false");

        return response;
    }
}
