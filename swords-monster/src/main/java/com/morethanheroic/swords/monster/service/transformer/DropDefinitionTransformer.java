package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.monster.domain.DropDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawDropDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DropDefinitionTransformer {

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private DropAmountDefinitionTransformer dropAmountDefinitionTransformer;

    public DropDefinition transform(RawDropDefinition rawDropDefinition) {
        return DropDefinition.builder()
                .amount(dropAmountDefinitionTransformer.transform(rawDropDefinition.getAmount()))
                .item(itemDefinitionCache.getDefinition(rawDropDefinition.getItem()))
                .chance(rawDropDefinition.getChance())
                .identified(rawDropDefinition.isIdentified())
                .build();
    }
}
