package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatEffectServiceAccessor {

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Autowired
    private InventoryFacade inventoryFacade;

    public UnidentifiedItemIdCalculator getUnidentifiedItemIdCalculator() {
        return unidentifiedItemIdCalculator;
    }

    public InventoryFacade getInventoryFacade() {
        return inventoryFacade;
    }
}
