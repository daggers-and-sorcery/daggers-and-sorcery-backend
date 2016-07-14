package com.morethanheroic.swords.profile.service.response.item;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.response.UnidentifiedItemEntryResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProfileUnidentifiedItemEntryResponseBuilder {

    @Autowired
    private UnidentifiedItemEntryResponseBuilder unidentifiedItemEntryResponseBuilder;

    public Map<String, Object> buildItemEntry(ItemDefinition itemDefinition, int replacementId) {
        final Map<String, Object> result = unidentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinition);

        result.put("id", replacementId);

        return result;
    }
}
