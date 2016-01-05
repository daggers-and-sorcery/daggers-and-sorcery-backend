package com.morethanheroic.swords.item.service.response;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UnidentifiedItemEntryResponseBuilder extends ItemEntryResponseBuilder {

    @Override
    public HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        HashMap<String, Object> itemData = super.buildItemEntry(itemDefinition);

        itemData.put("name", "Unidentified item");
        itemData.remove("modifiers");
        itemData.remove("requirements");

        return itemData;
    }
}
