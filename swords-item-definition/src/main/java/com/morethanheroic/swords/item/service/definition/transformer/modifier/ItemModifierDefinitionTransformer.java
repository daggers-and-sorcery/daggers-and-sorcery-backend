package com.morethanheroic.swords.item.service.definition.transformer.modifier;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.domain.modifier.ItemModifierDefinition;
import com.morethanheroic.swords.item.service.definition.loader.domain.RawItemModifierDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform a {@link RawItemModifierDefinition} to a {@link ItemModifierDefinition}.
 */
@Service
public class ItemModifierDefinitionTransformer implements DefinitionTransformer<ItemModifierDefinition, RawItemModifierDefinition> {

    @Override
    public ItemModifierDefinition transform(RawItemModifierDefinition rawItemModifierDefinition) {
        return ItemModifierDefinition.builder()
                .amount(rawItemModifierDefinition.getAmount())
                .d2(rawItemModifierDefinition.getD2())
                .d4(rawItemModifierDefinition.getD4())
                .d6(rawItemModifierDefinition.getD6())
                .d8(rawItemModifierDefinition.getD8())
                .d10(rawItemModifierDefinition.getD10())
                .modifier(rawItemModifierDefinition.getModifier())
                .build();
    }
}
