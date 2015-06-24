package com.swordssorcery.server.controller.character;

import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.AttributeType;
import com.swordssorcery.server.game.attribute.AttributeUtil;
import com.swordssorcery.server.game.attribute.data.AttributeData;
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
import java.util.LinkedHashMap;

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
    public HashMap<AttributeType, LinkedHashMap<String, AttributeData>> info(HttpSession session) {
        User user = userRepository.findOne((String) session.getAttribute(SessionAttributeType.USER_ID));

        HashMap<AttributeType, LinkedHashMap<String, AttributeData>> response = new HashMap<>();

        for (AttributeType attributeType : AttributeType.values()) {
            LinkedHashMap<String, AttributeData> attributeDataHolder = new LinkedHashMap<>();
            response.put(attributeType, attributeDataHolder);

            attributeUtil.getAllAttributes().stream().filter(attribute -> attribute.getAttributeType() == attributeType)
                    .forEach(attribute -> attributeDataHolder.put(attribute.getName(), attributeCalculator.calculateAttributeValue(user, attribute)));
        }

        return response;
    }
}
