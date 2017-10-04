package com.morethanheroic.swords.loot.service.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.loot.domain.LootDefinition;
import com.morethanheroic.swords.loot.service.loader.domain.RawLootDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LootDefinitionTransformer implements DefinitionTransformer<LootDefinition, RawLootDefinition> {

    private final LootDropDefinitionTransformer dropDefinitionTransformer;

    @Override
    public LootDefinition transform(RawLootDefinition rawDefinition) {
        return LootDefinition.builder()
                .id(rawDefinition.getId())
                .dropDefinitions(
                        rawDefinition.getDroplist().stream().map(dropDefinitionTransformer::transform)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
