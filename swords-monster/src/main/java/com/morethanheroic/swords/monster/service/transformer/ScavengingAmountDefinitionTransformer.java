package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.monster.domain.ScavengingAmountDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawScavengingAmountDefinition;
import org.springframework.stereotype.Service;

@Service
public class ScavengingAmountDefinitionTransformer {

    public ScavengingAmountDefinition transform(RawScavengingAmountDefinition rawScavengingAmountDefinition) {
        ScavengingAmountDefinition.ScavengingAmountDefinitionBuilder scavengingAmountDefinitionBuilder = new ScavengingAmountDefinition.ScavengingAmountDefinitionBuilder();

        scavengingAmountDefinitionBuilder.setMinimumAmount(rawScavengingAmountDefinition.getMin());
        scavengingAmountDefinitionBuilder.setMaximumAmount(rawScavengingAmountDefinition.getMax());

        return scavengingAmountDefinitionBuilder.build();
    }
}
