package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.AttributeFacade;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.session.SessionAttributeType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLoginController {

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final ShaPasswordEncoder shaPasswordEncoder;

    @NonNull
    private final AttributeFacade attributeFacade;

    @NonNull
    private final ResponseFactory responseFactory;

    @NonNull
    private final ResponseFactory responseFactory;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Response login(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException {
        //TODO: Move this to UserFacade (UserManager atm) when it's moved out of the main swords package into its own module.
        final UserDatabaseEntity user = userMapper.findByUsernameAndPassword(username, shaPasswordEncoder.encodePassword(password, null));
        final UserEntity userEntity = new UserEntity(user.getId(), userMapper);

        final Response response = responseFactory.newResponse(userEntity);

        //In this if use something like is userFacade.isUserExists(username, password) and move getUser inside the if
        if (userEntity != null) {
            response.setData("success", "true");

            session.setAttribute(SessionAttributeType.USER_ID.name(), user.getId());

            userMapper.updateLastLoginDate(user);
        } else {
            response.setData("success", "false");
            response.setData("error", "Wrong username or password!");
        }

        return response;
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Response info(UserEntity user) {
        Response response = responseFactory.newResponse(user);

        if(user != null) {
            response.setData("loggedIn", true);
            response.setData("life", attributeFacade.calculateAttributeValue(user, CombatAttribute.LIFE).getValue());
            response.setData("max_life", attributeFacade.calculateAttributeMaximumValue(user, CombatAttribute.LIFE).getValue());
            response.setData("mana", attributeFacade.calculateAttributeValue(user, CombatAttribute.MANA).getValue());
            response.setData("max_mana", attributeFacade.calculateAttributeMaximumValue(user, CombatAttribute.MANA).getValue());
            response.setData("movement", attributeFacade.calculateAttributeValue(user, BasicAttribute.MOVEMENT).getValue());
            response.setData("max_movement", attributeFacade.calculateAttributeMaximumValue(user, BasicAttribute.MOVEMENT).getValue());
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
