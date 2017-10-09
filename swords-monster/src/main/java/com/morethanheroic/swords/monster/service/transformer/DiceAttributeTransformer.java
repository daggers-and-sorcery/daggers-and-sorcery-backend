package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.monster.service.loader.domain.RawDiceAttribute;
import org.springframework.stereotype.Service;

@Service
public class DiceAttributeTransformer {

    public DiceAttribute transform(RawDiceAttribute rawDiceAttribute) {
        return DiceAttribute.builder()
                .value(rawDiceAttribute.getValue())
                .d2(rawDiceAttribute.getD2())
                .d4(rawDiceAttribute.getD4())
                .d6(rawDiceAttribute.getD6())
                .d8(rawDiceAttribute.getD8())
                .d10(rawDiceAttribute.getD10())
                .build();
    }
}
