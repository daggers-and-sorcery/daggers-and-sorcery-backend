package com.morethanheroic.swords.starter.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.starter.service.domain.StarterArmorSet;
import com.morethanheroic.swords.starter.service.domain.StartingArmor;

/**
 * Calculate the starter armor's {@link StarterArmorSet} from the given {@link StarterArmorSet}.
 */
@Service
public class StarterArmorCalculator {

    private static final int BEATEN_WOODEN_BUCKLER_ID = 133;
    private static final int CORRODED_BRONZE_BOOTS_ID = 130;
    private static final int CORRODED_BRONZE_CHESTPLATE_ID = 131;
    private static final int WORN_LEATHER_BOOTS_ID = 134;
    private static final int WORN_LEATHER_TUNIC_ID = 135;
    private static final int ROUGHLY_STICHED_ROBES_ID = 136;
    private static final int WORN_SANDALS_ID = 137;
    private static final int SPELLBOOK_ID = 132;

    private final Map<StartingArmor, StarterArmorSet> startingArmorMap;

    public StarterArmorCalculator(final ItemDefinitionCache itemDefinitionCache) {
        startingArmorMap = ImmutableMap.<StartingArmor, StarterArmorSet>builder()
            .put(StartingArmor.HEAVY,
                StarterArmorSet.builder()
                    .armor(itemDefinitionCache.getDefinition(CORRODED_BRONZE_CHESTPLATE_ID))
                    .offhand(itemDefinitionCache.getDefinition(BEATEN_WOODEN_BUCKLER_ID))
                    .boots(itemDefinitionCache.getDefinition(CORRODED_BRONZE_BOOTS_ID))
                    .build()
            )
            .put(StartingArmor.LIGHT,
                StarterArmorSet.builder()
                    .armor(itemDefinitionCache.getDefinition(WORN_LEATHER_TUNIC_ID))
                    .offhand(itemDefinitionCache.getDefinition(BEATEN_WOODEN_BUCKLER_ID))
                    .boots(itemDefinitionCache.getDefinition(WORN_LEATHER_BOOTS_ID))
                    .build()
            )
            .put(StartingArmor.CLOTH,
                StarterArmorSet.builder()
                    .armor(itemDefinitionCache.getDefinition(ROUGHLY_STICHED_ROBES_ID))
                    .offhand(itemDefinitionCache.getDefinition(SPELLBOOK_ID))
                    .boots(itemDefinitionCache.getDefinition(WORN_SANDALS_ID))
                    .build()
            )
            .build();
    }

    /**
     * Calculate the starter armor's {@link StarterArmorSet} from the given {@link StartingArmor}.
     *
     * @param startingArmor the type of the starting armor
     */
    public StarterArmorSet getStarterArmor(final StartingArmor startingArmor) {
        if (startingArmorMap.containsKey(startingArmor)) {
            return startingArmorMap.get(startingArmor);
        }

        throw new IllegalStateException("Unknown starter armor!");
    }
}
