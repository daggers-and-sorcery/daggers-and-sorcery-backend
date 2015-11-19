package com.morethanheroic.swords.profile.service.response.item;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.response.UnidentifiedItemEntryResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProfileUnidentifiedItemEntryResponseBuilder {

    @Autowired
    private UnidentifiedItemEntryResponseBuilder unidentifiedItemEntryResponseBuilder;

    public HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition, int replacementId) {
        HashMap<String, Object> result = unidentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinition);

        result.put("id", replacementId);

        return result;
    }
}
