package com.morethanheroic.swords.profile.service;

import com.morethanheroic.swords.item.service.response.ItemEntryResponseBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProfileItemEntryResponseBuilder extends ItemEntryResponseBuilder {

    @Override
    public HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        HashMap<String, Object> result = super.buildItemEntry(itemDefinition);

        result.put("id", itemDefinition.getId());

        return result;
    }
}
