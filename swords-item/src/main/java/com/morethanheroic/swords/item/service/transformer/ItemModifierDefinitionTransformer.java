package com.morethanheroic.swords.item.service.transformer;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.item.domain.ItemModifierDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemModifierDefinition;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class ItemModifierDefinitionTransformer {

    public List<ItemModifierDefinition> transform(List<RawItemModifierDefinition> rawItemModifierDefinitionList) {
        if (rawItemModifierDefinitionList == null) {
            return Collections.emptyList();
        }

        return rawItemModifierDefinitionList.stream().map(this::transform).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

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
