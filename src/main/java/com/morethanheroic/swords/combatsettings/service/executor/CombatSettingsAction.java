package com.morethanheroic.swords.combatsettings.service.executor;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.UseSpellService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CombatSettingsAction {

    private static final Logger logger = LoggerFactory.getLogger(CombatSettingsAction.class);

    private final UseItemService useItemService;
    private final ItemDefinitionManager itemDefinitionManager;
    private final UseSpellService useSpellService;
    private final SpellDefinitionManager spellDefinitionManager;

    public CombatSettingsAction(UseItemService useItemService, ItemDefinitionManager itemDefinitionManager, UseSpellService useSpellService, SpellDefinitionManager spellDefinitionManager) {
        this.useItemService = useItemService;
        this.itemDefinitionManager = itemDefinitionManager;
        this.useSpellService = useSpellService;
        this.spellDefinitionManager = spellDefinitionManager;
    }

    public abstract void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity);

    //TODO: Break this to pieces based on SettingType
    protected void executeCombatSettings(UserCombatEntity userEntity, CombatSettingsEntity combatSettingsEntity) {
        logger.debug("Running combat settings: " + combatSettingsEntity);

        if (combatSettingsEntity.getType() == SettingType.ITEM) {
            useItemService.useItem(userEntity, itemDefinitionManager.getItemDefinition(combatSettingsEntity.getSettingsId()));
        } else {
            useSpellService.useSpell(userEntity, spellDefinitionManager.getSpellDefinition(combatSettingsEntity.getSettingsId()));
        }
    }
}
