package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
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

    private final UserMapper userRepository;
    private final ShaPasswordEncoder shaPasswordEncoder;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ResponseFactory responseFactory;

    @Autowired
    private UserLoginController(UserMapper userMapper, ShaPasswordEncoder shaPasswordEncoder, GlobalAttributeCalculator globalAttributeCalculator, ResponseFactory responseFactory) {
        this.userRepository = userMapper;
        this.shaPasswordEncoder = shaPasswordEncoder;
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.responseFactory = responseFactory;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public HashMap<String, String> login(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException {
        UserDatabaseEntity user = userRepository.findByUsernameAndPassword(username, shaPasswordEncoder.encodePassword(password, null));

        HashMap<String, String> response = new HashMap<>();

        if (user != null) {
            response.put("success", "true");

            session.setAttribute(SessionAttributeType.USER_ID, user.getId());

            userRepository.updateLastLoginDate(user);
        } else {
            response.put("success", "false");
            response.put("error", "Wrong username or password!");
        }

        return response;
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Response info(UserEntity user) {
        Response response = responseFactory.newResponse(user);

        if(user != null) {
            response.setData("loggedIn", true);
            response.setData("life", globalAttributeCalculator.calculateActualValue(user, CombatAttribute.LIFE).getValue());
            response.setData("max_life", globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.LIFE));
            response.setData("mana", globalAttributeCalculator.calculateActualValue(user, CombatAttribute.MANA).getValue());
            response.setData("max_mana", globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.MANA));
            response.setData("movement", globalAttributeCalculator.calculateActualValue(user, BasicAttribute.MOVEMENT).getValue());
            response.setData("max_movement", globalAttributeCalculator.calculateMaximumValue(user, BasicAttribute.MOVEMENT));
        } else {
            response.setData("loggedIn", false);
        }

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
