package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.monster.service.loader.domain.RawDiceAttribute;
import org.springframework.stereotype.Service;

@Service
public class DiceAttributeTransformer {

    public DiceAttribute transform(RawDiceAttribute rawDiceAttribute) {
        DiceAttribute.DiceAttributeBuilder diceAttributeBuilder = new DiceAttribute.DiceAttributeBuilder();

        diceAttributeBuilder.setValue(rawDiceAttribute.getValue());
        diceAttributeBuilder.setD10(rawDiceAttribute.getD10());
        diceAttributeBuilder.setD8(rawDiceAttribute.getD8());
        diceAttributeBuilder.setD6(rawDiceAttribute.getD6());
        diceAttributeBuilder.setD4(rawDiceAttribute.getD4());
        diceAttributeBuilder.setD2(rawDiceAttribute.getD2());

        return diceAttributeBuilder.build();
    }
}
