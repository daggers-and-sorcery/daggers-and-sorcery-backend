package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.recipe.service.RecipeFacade;
import com.morethanheroic.swords.spell.service.SpellLearningService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CombatEffectServiceAccessor {

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Autowired
    private SpellLearningService spellLearningService;

    @Autowired
    private SpellDefinitionCache spellDefinitionCache;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private RecipeFacade recipeFacade;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;
}
