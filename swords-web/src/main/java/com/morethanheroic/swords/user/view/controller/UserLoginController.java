package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.AttributeFacade;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.security.PasswordEncoder;
import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.dao.UserDatabaseEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLoginController {

    private static final UserEntity LOGGED_OUT_USER = null;

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final AttributeFacade attributeFacade;

    @NonNull
    private final ResponseFactory responseFactory;

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Response login(HttpSession session, @RequestParam String username, @RequestParam String password) throws UnsupportedEncodingException {
        //TODO: Move this to UserFacade (UserManager atm) when it's moved out of the main swords package into its own module.
        final UserDatabaseEntity userDatabaseEntity = userMapper.findByUsernameAndPassword(username, passwordEncoder.encodePassword(password));

        //TODO: In this if use something like is userFacade.isExistingUser(userEntity) and move getUser inside the if
        //TODO: Create response builders for this and separate action from response building.
        if (userDatabaseEntity != null) {
            final UserEntity userEntity = new UserEntity(userDatabaseEntity.getId(), userMapper);
            final Response response = responseFactory.newResponse(userEntity);

            response.setData("success", "true");

            session.setAttribute(SessionAttributeType.USER_ID.name(), userDatabaseEntity.getId());

            userMapper.updateLastLoginDate(userDatabaseEntity);

            return response;
        } else {
            final Response response = responseFactory.newResponse(LOGGED_OUT_USER);

            response.setData("success", "false");
            response.setData("error", "Wrong username or password!");

            return response;
        }
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Response info(UserEntity user) {
        final Response response = responseFactory.newResponse(user);

        //TODO: Use userFacade.isExistingUser(userEntity) instead of this if.
        if (user != null) {
            response.setData("loggedIn", true);
            //TODO: Do we really need to set this data? It's automatically set afaik.
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
    public Response logout(HttpSession session) {
        session.invalidate();

        final Response response = responseFactory.newResponse(LOGGED_OUT_USER);

        response.setData("loggedIn", "false");

        return response;
    }
}
