package com.swordssorcery.server.controller.character;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.AttributeType;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.game.attribute.data.DefaultAttributeData;
import com.swordssorcery.server.model.repository.UserRepository;
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

    @ResponseBody
    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public HashMap<String, LinkedHashMap<String, AttributeData>> info(HttpSession session) {
        HashMap<String, LinkedHashMap<String, AttributeData>> response = new HashMap<>();

        for (AttributeType attributeType : AttributeType.values()) {
            LinkedHashMap<String, AttributeData> attributeDataHolder = new LinkedHashMap<>();
            response.put(attributeType.getShortName(), attributeDataHolder);

            for (Attribute attribute : Attribute.values()) {
                if (attribute.getAttributeType() == attributeType) {
                    attributeDataHolder.put(attribute.name(), attributeCalculator.calculateAttributeValue(null, attribute));
                }
            }
        }

        return response;
    }
}
