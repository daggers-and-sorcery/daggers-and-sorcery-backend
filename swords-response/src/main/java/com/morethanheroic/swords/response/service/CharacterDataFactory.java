package com.morethanheroic.swords.response.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.metadata.domain.TextMetadataEntity;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.response.domain.CharacterData;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterDataFactory {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final MetadataEntityFactory metadataEntityFactory;

    public CharacterData newInstance(final UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        final AttributeData healthAttributeData = globalAttributeCalculator.calculateAttributeValue(userEntity, CombatAttribute.LIFE);
        final AttributeData manaAttributeData = globalAttributeCalculator.calculateAttributeValue(userEntity, CombatAttribute.MANA);
        final AttributeData movementAttributeData = globalAttributeCalculator.calculateAttributeValue(userEntity, BasicAttribute.MOVEMENT);

        final TextMetadataEntity preludeMetadata = metadataEntityFactory.getTextEntity(userEntity, "PRELUDE_SHOWN");

        return CharacterData.builder()
                .actualHealth(healthAttributeData.getActual().getValue())
                .maximumHealth(healthAttributeData.getMaximum().getValue())
                .actualMana(manaAttributeData.getActual().getValue())
                .maximumMana(manaAttributeData.getMaximum().getValue())
                .actualMovement(movementAttributeData.getActual().getValue())
                .maximumMovement(movementAttributeData.getMaximum().getValue())
                .preludeShown(preludeMetadata.getValue().equals("ALREADY_SHOWN"))
                .build();
    }
}
