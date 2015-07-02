package com.swordssorcery.server.response.character;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeCalculator;
import com.swordssorcery.server.game.attribute.AttributeUtil;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.model.User;
import com.swordssorcery.server.response.Response;
import com.swordssorcery.server.response.ResponseBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class CharacterInfoResponseBuilderService implements ResponseBuilderService {

    @Autowired
    private AttributeCalculator attributeCalculator;
    @Autowired
    private AttributeUtil attributeUtil;

    @Override
    public Response build(User user) {
        Response response = new Response();

        LinkedList<AttributeData> attributeData = new LinkedList<>();
        response.setData("attribute", attributeData);

        for (Attribute attribute : attributeUtil.getAllAttributes()) {
            attributeData.add(attributeCalculator.calculateAttributeValue(user, attribute));
        }

        response.setData("username", user.getUsername());
        response.setData("race", user.getRace());
        response.setData("registrationDate", user.getRegistrationDate());
        response.setData("lastLoginDate", user.getLastLoginDate());

        return response;
    }
}
