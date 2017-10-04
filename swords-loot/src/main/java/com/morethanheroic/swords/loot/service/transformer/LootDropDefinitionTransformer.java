package com.morethanheroic.swords.loot.service.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.loot.domain.DropDefinition;
import com.morethanheroic.swords.loot.service.loader.domain.RawDropDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LootDropDefinitionTransformer implements DefinitionTransformer<DropDefinition, RawDropDefinition> {

    private final ItemDefinitionCache itemDefinitionCache;
    private final LootAmountDefinitionTransformer dropAmountDefinitionTransformer;

    @Override
    public DropDefinition transform(RawDropDefinition rawDefinition) {
        return DropDefinition.builder()
                .amount(dropAmountDefinitionTransformer.transform(rawDefinition.getAmount()))
                .item(itemDefinitionCache.getDefinition(rawDefinition.getItem()))
                .chance(rawDefinition.getChance())
                .identified(rawDefinition.isIdentified())
                .build();
    }
}
