package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class LearnSpellEffectDefinition extends CombatEffectDefinition {

    private final int spellItemId;
    private final int spellId;

    public LearnSpellEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        spellItemId = Integer.parseInt(this.getEffectSetting("spell-item-id").getValue());
        spellId = Integer.parseInt(this.getEffectSetting("spell-id").getValue());
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatResult combatResult, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        final UserEntity userEntity = ((UserCombatEntity) combatEntity).getUserEntity();
        final SpellDefinition spellDefinition = combatEffectServiceAccessor.getSpellDefinitionCache().getSpellDefinition(spellId);

        if(!combatEffectServiceAccessor.getSpellLearningService().hasSpellLearned(userEntity, spellDefinition)) {
            combatEffectServiceAccessor.getSpellLearningService().learnSpell(userEntity, spellDefinition);

            combatEffectServiceAccessor.getInventoryFacade().getInventory(((UserCombatEntity) combatEntity).getUserEntity()).removeItem(spellItemId, 1);
        }
    }
}
