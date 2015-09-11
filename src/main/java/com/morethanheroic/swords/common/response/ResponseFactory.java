package com.morethanheroic.swords.common.response;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
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

    public Response newResponse(UserEntity user) {
        return new Response(buildCharacterData(user));
    }

    private CharacterData buildCharacterData(UserEntity user) {
        if(user == null) {
            return null;
        }

        return new CharacterData(
                globalAttributeCalculator.calculateActualValue(user, BasicAttribute.MOVEMENT),
                globalAttributeCalculator.calculateActualValue(user, CombatAttribute.LIFE),
                globalAttributeCalculator.calculateActualValue(user, CombatAttribute.MANA)
        );
    }
}
