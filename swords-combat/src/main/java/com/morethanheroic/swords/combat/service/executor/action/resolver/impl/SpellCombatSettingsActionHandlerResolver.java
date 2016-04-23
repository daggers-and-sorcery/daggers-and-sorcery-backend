package com.morethanheroic.swords.combat.service.executor.action.resolver.impl;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolver;
import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SpellCombatSettingsActionHandlerResolver implements CombatSettingsActionHandlerResolver {

    private final UseSpellService useSpellService;
    private final SpellDefinitionCache spellDefinitionCache;

    @Override
    public void handleSettings(UserCombatEntity userEntity, CombatSettingsEntity combatSettingsEntity, CombatEffectDataHolder combatEffectDataHolder) {
        useSpellService.useSpell(userEntity, spellDefinitionCache.getSpellDefinition(combatSettingsEntity.getSettingsId()), combatEffectDataHolder);
    }

    @Override
    public SettingType getSupportedSettingType() {
        return SettingType.SPELL;
    }
}
