package com.morethanheroic.swords.response.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.response.domain.CharacterData;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public ResponseFactory(GlobalAttributeCalculator globalAttributeCalculator) {
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    public Response newResponse(UserEntity userEntity) {
        return new Response(buildCharacterData(userEntity));
    }

    public Response newSuccessfulResponse(UserEntity userEntity) {
        return buildStatusResponse(userEntity, true);
    }

    public Response newFailedResponse(UserEntity userEntity) {
        return buildStatusResponse(userEntity, false);
    }

    private Response buildStatusResponse(UserEntity userEntity, boolean status) {
        Response response = newResponse(userEntity);

        response.setData("success", status);

        return response;
    }

    private CharacterData buildCharacterData(UserEntity user) {
        if (user == null) {
            return null;
        }

        return new CharacterData(
                globalAttributeCalculator.calculateActualValue(user, BasicAttribute.MOVEMENT).getValue(),
                globalAttributeCalculator.calculateActualValue(user, CombatAttribute.LIFE).getValue(),
                globalAttributeCalculator.calculateActualValue(user, CombatAttribute.MANA).getValue()
        );
    }
}
