package com.morethanheroic.swords.combatsettings.service.executor;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CombatSettingsAction {

    private static final Logger logger = LoggerFactory.getLogger(CombatSettingsAction.class);

    private final UseItemService useItemService;

    public CombatSettingsAction(UseItemService useItemService) {
        this.useItemService = useItemService;
    }

    public abstract void executeAction(CombatResult result, Combat combat, CombatSettingsEntity combatSettingsEntity);

    //TODO: Break this to pieces based on SettingType
    protected void executeCombatSettings(UserEntity userEntity, CombatSettingsEntity combatSettingsEntity) {
        logger.debug("Running combat settings: " + combatSettingsEntity);

        if (combatSettingsEntity.getType() == SettingType.ITEM) {
            useItemService.useItem(userEntity, combatSettingsEntity.getSettingsId());
        } else {
            //TODO: if spell is a non attack spell use it here
        }
    }
}
