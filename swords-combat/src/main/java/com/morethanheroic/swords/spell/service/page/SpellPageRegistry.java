package com.morethanheroic.swords.spell.service.page;

import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.response.UnidentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.spell.service.page.entry.LesserIdentificationPageEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpellPageRegistry {

    @Autowired
    private SpellDefinitionCache spellDefinitionCache;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private UnidentifiedItemEntryResponseBuilder unidentifiedItemEntryResponseBuilder;

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    private Map<SpellDefinition, SpellPageEntry> spellPageEntryMap = new HashMap<>();

    @PostConstruct
    private void initialize() {
        spellPageEntryMap.put(spellDefinitionCache.getSpellDefinition(3), buildLesserIdentificationPage());
    }

    public SpellPageEntry getSpellPageEntry(SpellDefinition spellDefinition) {
        return spellPageEntryMap.get(spellDefinition);
    }

    private SpellPageEntry buildLesserIdentificationPage() {
        return new LesserIdentificationPageEntry(inventoryFacade, responseFactory, itemDefinitionCache, unidentifiedItemEntryResponseBuilder, unidentifiedItemIdCalculator);
    }
}
