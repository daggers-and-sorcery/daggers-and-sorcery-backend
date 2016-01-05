package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.monster.domain.DropAmountDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawDropAmountDefinition;
import org.springframework.stereotype.Service;

@Service
public class DropAmountDefinitionTransformer {

    public DropAmountDefinition transform(RawDropAmountDefinition rawDropAmountDefinition) {
        DropAmountDefinition.DropAmountDefinitionBuilder dropAmountDefinitionBuilder = new DropAmountDefinition.DropAmountDefinitionBuilder();

        dropAmountDefinitionBuilder.setMinimumAmount(rawDropAmountDefinition.getMin());
        dropAmountDefinitionBuilder.setMaximumAmount(rawDropAmountDefinition.getMax());

        return dropAmountDefinitionBuilder.build();
    }
}
