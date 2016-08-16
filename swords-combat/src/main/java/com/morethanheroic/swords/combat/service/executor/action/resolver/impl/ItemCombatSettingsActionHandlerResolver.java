package com.morethanheroic.swords.combat.service.executor.action.resolver.impl;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolver;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Deprecated
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ItemCombatSettingsActionHandlerResolver implements CombatSettingsActionHandlerResolver {

    private final UseItemService useItemService;
    private final ItemDefinitionCache itemDefinitionCache;
    private final CombatMessageBuilder combatMessageBuilder;

    @Override
    public void handleSettings(Combat combat, CombatResult combatResult, CombatSettingsEntity combatSettingsEntity, CombatEffectDataHolder combatEffectDataHolder) {
        final ItemDefinition itemToUse = itemDefinitionCache.getDefinition(combatSettingsEntity.getSettingsId());

        combatResult.addMessage(combatMessageBuilder.buildUseItemMessage(itemToUse.getName()));

        //useItemService.useItem(combat.getUserCombatEntity(), combat, combatResult, itemToUse, combatEffectDataHolder);
    }

    @Override
    public SettingType getSupportedSettingType() {
        return SettingType.ITEM;
    }
}
