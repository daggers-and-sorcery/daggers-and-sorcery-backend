package com.morethanheroic.swords.item.service.response;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Build a response for an unidentified item.
 */
@Service
public class UnidentifiedItemEntryResponseBuilder extends ItemEntryResponseBuilder {

    @Override
    public Map<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        final Map<String, Object> itemData = super.buildItemEntry(itemDefinition);

        itemData.put("name", "Unidentified item");
        itemData.remove("modifiers");
        itemData.remove("requirements");

        return itemData;
    }
}
