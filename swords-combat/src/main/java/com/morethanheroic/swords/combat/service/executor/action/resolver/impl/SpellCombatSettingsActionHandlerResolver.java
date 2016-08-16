package com.morethanheroic.swords.combat.service.executor.action.resolver.impl;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.executor.action.resolver.CombatSettingsActionHandlerResolver;
import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO: remove
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Deprecated
public class SpellCombatSettingsActionHandlerResolver implements CombatSettingsActionHandlerResolver {

    private final UseSpellService useSpellService;
    private final SpellDefinitionCache spellDefinitionCache;
    private final CombatMessageBuilder combatMessageBuilder;

    @Override
    public void handleSettings(Combat combat, CombatResult combatResult, CombatSettingsEntity combatSettingsEntity, CombatEffectDataHolder combatEffectDataHolder) {
        final SpellDefinition spellToUse = spellDefinitionCache.getSpellDefinition(combatSettingsEntity.getSettingsId());

        combatResult.addMessage(combatMessageBuilder.buildUseSpellMessage(spellToUse.getName()));

        //useSpellService.useSpell(combat, combatResult, spellToUse, combatEffectDataHolder);
    }

    @Override
    public SettingType getSupportedSettingType() {
        return SettingType.SPELL;
    }
}
