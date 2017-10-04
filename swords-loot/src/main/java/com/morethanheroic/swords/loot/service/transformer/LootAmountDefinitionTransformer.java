package com.morethanheroic.swords.loot.service.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.loot.domain.DropAmountDefinition;
import com.morethanheroic.swords.loot.service.loader.domain.RawDropAmountDefinition;
import org.springframework.stereotype.Service;

@Service
public class LootAmountDefinitionTransformer implements DefinitionTransformer<DropAmountDefinition, RawDropAmountDefinition> {

    @Override
    public DropAmountDefinition transform(RawDropAmountDefinition rawDefinition) {
        return DropAmountDefinition.builder()
                .minimumAmount(rawDefinition.getMin())
                .maximumAmount(rawDefinition.getMax())
                .build();
    }
}
