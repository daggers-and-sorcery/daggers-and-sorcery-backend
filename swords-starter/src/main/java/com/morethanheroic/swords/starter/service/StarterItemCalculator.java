package com.morethanheroic.swords.starter.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.starter.service.domain.StartingArmor;
import com.morethanheroic.swords.starter.service.domain.StartingItem;

/**
 * Calculate the starter items of a player based on their armor choice.
 */
@Service
public class StarterItemCalculator {

    private static final int APPLE_PIE_ID = 27;
    private static final int FRIED_PEANUT_ID = 58;
    private static final int SPELL_TOME_FIREBALL_ID = 34;
    private static final int SPELL_TOME_HEAL_ID = 33;

    private final Map<StartingArmor, List<StartingItem>> starterItems;

    public StarterItemCalculator(final ItemDefinitionCache itemDefinitionCache) {
        starterItems =  ImmutableMap.<StartingArmor, List<StartingItem>>builder()
            .put(StartingArmor.HEAVY, Lists.newArrayList(
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(APPLE_PIE_ID))
                        .itemAmount(2)
                        .build(),
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(FRIED_PEANUT_ID))
                        .itemAmount(5)
                        .build()
                )
            )
            .put(StartingArmor.LIGHT, Lists.newArrayList(
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(APPLE_PIE_ID))
                        .itemAmount(2)
                        .build(),
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(FRIED_PEANUT_ID))
                        .itemAmount(5)
                        .build()
                )
            )
            .put(StartingArmor.ROBE, Lists.newArrayList(
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(APPLE_PIE_ID))
                        .itemAmount(2)
                        .build(),
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(FRIED_PEANUT_ID))
                        .itemAmount(5)
                        .build(),
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(SPELL_TOME_FIREBALL_ID))
                        .itemAmount(1)
                        .build(),
                    StartingItem.builder()
                        .itemDefinition(itemDefinitionCache.getDefinition(SPELL_TOME_HEAL_ID))
                        .itemAmount(1)
                        .build()
                )
            )
            .build();
    }

    public List<StartingItem> getStartingItems(final StartingArmor startingArmor) {
        if (starterItems.containsKey(startingArmor)) {
            return starterItems.get(startingArmor);
        }

        throw new IllegalStateException("Unknown starter item pack!");
    }
}
