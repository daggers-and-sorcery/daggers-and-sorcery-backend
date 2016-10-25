package com.morethanheroic.swords.response.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.response.domain.CharacterData;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterDataFactory {

    private final GlobalAttributeCalculator globalAttributeCalculator;

    public CharacterData newInstance(final UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new CharacterData(
                globalAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.MOVEMENT).getValue(),
                globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.LIFE).getValue(),
                globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.MANA).getValue()
        );
    }
}
