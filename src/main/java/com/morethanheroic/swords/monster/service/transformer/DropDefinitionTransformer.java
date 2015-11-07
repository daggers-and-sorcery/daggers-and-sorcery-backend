package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.monster.domain.DropDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawDropDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DropDefinitionTransformer {

    @Autowired
    private DropAmountDefinitionTransformer dropAmountDefinitionTransformer;

    public DropDefinition transform(RawDropDefinition rawDropDefinition) {
        DropDefinition.DropDefinitionBuilder dropDefinitionBuilder = new DropDefinition.DropDefinitionBuilder();

        dropDefinitionBuilder.setAmount(dropAmountDefinitionTransformer.transform(rawDropDefinition.getAmount()));
        dropDefinitionBuilder.setItem(rawDropDefinition.getItem());
        dropDefinitionBuilder.setChance(rawDropDefinition.getChance());

        return dropDefinitionBuilder.build();
    }
}
