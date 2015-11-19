package com.morethanheroic.swords.profile.service.response.item;

import com.morethanheroic.swords.item.service.response.ItemEntryResponseBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProfileIdentifiedItemEntryResponseBuilder {

    @Autowired
    private ItemEntryResponseBuilder itemEntryResponseBuilder;

    public HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        HashMap<String, Object> result = itemEntryResponseBuilder.buildItemEntry(itemDefinition);

        result.put("id", itemDefinition.getId());

        return result;
    }
}
