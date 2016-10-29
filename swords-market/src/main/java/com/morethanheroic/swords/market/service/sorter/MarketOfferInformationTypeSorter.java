package com.morethanheroic.swords.market.service.sorter;

import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.service.sorter.ItemTypeSorter;
import com.morethanheroic.swords.market.domain.MarketOfferInformationEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketOfferInformationTypeSorter implements ItemTypeSorter<MarketOfferInformationEntry> {

    @Override
    public Map<ItemType, List<MarketOfferInformationEntry>> sortByType(List<MarketOfferInformationEntry> inventoryItems) {
        final Map<ItemType, List<MarketOfferInformationEntry>> result = new HashMap<>();

        for (MarketOfferInformationEntry inventoryItem : inventoryItems) {
            final ItemType itemType = inventoryItem.getItem().getType();

            if (!result.containsKey(itemType)) {
                result.put(itemType, new ArrayList<>());
            }

            result.get(itemType).add(inventoryItem);
        }

        return Collections.unmodifiableMap(result);
    }
}
