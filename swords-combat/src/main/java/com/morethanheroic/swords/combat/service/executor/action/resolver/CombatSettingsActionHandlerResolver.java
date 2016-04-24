package com.morethanheroic.swords.combat.service.executor.action.resolver;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;

/**
 * Base class to handle settings actions.
 */
public interface CombatSettingsActionHandlerResolver {

    void handleSettings(UserCombatEntity userEntity, CombatResult combatResult, CombatSettingsEntity combatSettingsEntity, CombatEffectDataHolder combatEffectDataHolder);

    SettingType getSupportedSettingType();
}
