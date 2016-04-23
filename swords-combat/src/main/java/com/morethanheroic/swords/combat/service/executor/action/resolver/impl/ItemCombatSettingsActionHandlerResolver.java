package com.morethanheroic.swords.combat.service.executor.action.resolver.impl;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.UseItemService;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolver;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ItemCombatSettingsActionHandlerResolver implements CombatSettingsActionHandlerResolver {

    private final UseItemService useItemService;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public void handleSettings(UserCombatEntity userEntity, CombatSettingsEntity combatSettingsEntity, CombatEffectDataHolder combatEffectDataHolder) {
        useItemService.useItem(userEntity, itemDefinitionCache.getDefinition(combatSettingsEntity.getSettingsId()), combatEffectDataHolder);
    }

    @Override
    public SettingType getSupportedSettingType() {
        return SettingType.ITEM;
    }
}
