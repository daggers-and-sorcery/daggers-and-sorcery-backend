package com.morethanheroic.swords.settings.service.executor;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.UseSpellService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CombatSettingsAction {

    private static final Logger logger = LoggerFactory.getLogger(CombatSettingsAction.class);

    private final UseItemService useItemService;
    private final ItemDefinitionCache itemDefinitionCache;
    private final UseSpellService useSpellService;
    private final SpellDefinitionManager spellDefinitionManager;

    public CombatSettingsAction(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseSpellService useSpellService, SpellDefinitionManager spellDefinitionManager) {
        this.useItemService = useItemService;
        this.itemDefinitionCache = itemDefinitionCache;
        this.useSpellService = useSpellService;
        this.spellDefinitionManager = spellDefinitionManager;
    }

    public abstract void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity);

    protected void executeCombatSettings(UserCombatEntity userEntity, CombatSettingsEntity combatSettingsEntity) {
        logger.debug("Running combat settings: " + combatSettingsEntity);

        switch (combatSettingsEntity.getType()) {
            case ITEM:
                useItemService.useItem(userEntity, itemDefinitionCache.getItemDefinition(combatSettingsEntity.getSettingsId()));
                break;
            case SPELL:
                useSpellService.useSpell(userEntity, spellDefinitionManager.getSpellDefinition(combatSettingsEntity.getSettingsId()));
                break;
            default:
                throw new IllegalArgumentException("Unhandled combat setting type: " + combatSettingsEntity.getType());
        }
    }
}
