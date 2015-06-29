package com.swordssorcery.server.controller.character;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.AttributeUtil;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;
import com.swordssorcery.server.model.User;
import com.swordssorcery.server.model.repository.UserRepository;
import com.swordssorcery.server.session.SessionAttributeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;

@Controller
public class CharacterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttributeCalculator attributeCalculator;
    @Autowired
    private AttributeUtil attributeUtil;

    @ResponseBody
    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public HashMap<String, Object> info(HttpSession session) {
        User user = userRepository.findOne((String) session.getAttribute(SessionAttributeType.USER_ID));

        HashMap<String, Object> response = new HashMap<>();

        LinkedList<AttributeData> attributeData = new LinkedList<AttributeData>();
        response.put("attribute", attributeData);

        for (Attribute attribute : attributeUtil.getAllAttributes()) {
            attributeData.add(attributeCalculator.calculateAttributeValue(user, attribute));
        }

        response.put("username", user.getUsername());
        response.put("race", user.getRace());
        response.put("registrationDate", user.getRegistrationDate());
        response.put("lastLoginDate", user.getLastLoginDate());

        //TEST
        user.getSkills().addSkillXp(SkillAttribute.ALCHEMY, 10);
        userRepository.save(user);
        //--END TEST

        return response;
    }
}
