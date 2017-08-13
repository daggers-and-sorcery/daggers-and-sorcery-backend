package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.domain.WeaponSuperType;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Calculate the super type from an item type.
 */
@Service
public class WeaponSuperTypeCalculator {

    @SuppressWarnings("checkstyle:CyclomaticComplexity")
    public Optional<WeaponSuperType> calculateWeaponSuperType(final ItemType itemType) {
        if (itemType == null) {
            return Optional.empty();
        }

        switch (itemType) {
            case TWO_HANDED_CRUSHING_WEAPONS:
            case ONE_HANDED_CRUSHING_WEAPONS:
            case TWO_HANDED_AXES:
            case ONE_HANDED_AXES:
            case LONGSWORDS:
            case SHORTSWORDS:
            case POLEARMS:
            case DAGGERS:
                return Optional.of(WeaponSuperType.MEELE);
            case LONGBOWS:
            case SHORTBOWS:
            case CROSSBOWS:
            case THROWING_WEAPONS:
                return Optional.of(WeaponSuperType.RANGED);
            case STAFF:
            case WAND:
            case SCEPTRE:
                return Optional.of(WeaponSuperType.MAGIC);
            default:
                return Optional.empty();
        }
    }
}
