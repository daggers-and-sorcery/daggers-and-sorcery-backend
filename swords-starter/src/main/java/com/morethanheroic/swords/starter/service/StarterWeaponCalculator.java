package com.morethanheroic.swords.starter.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.starter.service.domain.StartingAmmunitionSet;
import com.morethanheroic.swords.starter.service.domain.StartingWeapon;
import com.morethanheroic.swords.starter.service.domain.StartingWeaponSet;

/**
 * Calculate the starter weapon's {@link ItemDefinition} from the given {@link StartingWeapon}.
 */
@Service
public class StarterWeaponCalculator {

    private static final int CHIPPED_BRONZE_AXE_ID = 138;
    private static final int LOOSELY_STRUNG_BOW_ID = 142;
    private static final int DULL_DAGGER_ID = 140;
    private static final int OLD_HAMMER_ID = 143;
    private static final int OLD_STAFF_ID = 144;
    private static final int DULL_SWORD_ID = 141;
    private static final int CRACKED_WAND_ID = 139;
    private static final int BRONZE_ARROW_ID = 6;

    private final Map<StartingWeapon, StartingWeaponSet> startingWeaponMap;

    public StarterWeaponCalculator(final ItemDefinitionCache itemDefinitionCache) {
        startingWeaponMap = ImmutableMap.<StartingWeapon, StartingWeaponSet>builder()
                    .put(
                        StartingWeapon.AXE, StartingWeaponSet.builder()
                            .weapon(itemDefinitionCache.getDefinition(CHIPPED_BRONZE_AXE_ID))
                            .build()
                    )
                    .put(
                        StartingWeapon.BOW, StartingWeaponSet.builder()
                            .weapon(itemDefinitionCache.getDefinition(LOOSELY_STRUNG_BOW_ID))
                            .quiver(
                                    StartingAmmunitionSet.builder()
                                        .ammunition(itemDefinitionCache.getDefinition(BRONZE_ARROW_ID))
                                        .amount(20)
                                        .build()
                            )
                            .build()
                    )
                    .put(
                        StartingWeapon.DAGGER, StartingWeaponSet.builder()
                            .weapon(itemDefinitionCache.getDefinition(DULL_DAGGER_ID))
                            .build()
                    )
                    .put(
                        StartingWeapon.HAMMER, StartingWeaponSet.builder()
                            .weapon(itemDefinitionCache.getDefinition(OLD_HAMMER_ID))
                            .build()
                    )
                    .put(
                        StartingWeapon.STAFF, StartingWeaponSet.builder()
                            .weapon(itemDefinitionCache.getDefinition(OLD_STAFF_ID))
                            .build()
                    )
                    .put(
                        StartingWeapon.SWORD, StartingWeaponSet.builder()
                            .weapon(itemDefinitionCache.getDefinition(DULL_SWORD_ID))
                            .build()
                    )
                    .put(
                        StartingWeapon.WAND, StartingWeaponSet.builder()
                            .weapon(itemDefinitionCache.getDefinition(CRACKED_WAND_ID))
                            .build()
                    )
                    .build();
    }

    /**
     * Calculate the starter weapon's {@link ItemDefinition} from the given {@link StartingWeapon}.
     *
     * @param startingWeapon the type of the starting weapon
     */
    public StartingWeaponSet getStarterWeapon(final StartingWeapon startingWeapon) {
        if (startingWeaponMap.containsKey(startingWeapon)) {
            return startingWeaponMap.get(startingWeapon);
        }

        throw new IllegalStateException("Unknown starter weapon!");
    }
}
