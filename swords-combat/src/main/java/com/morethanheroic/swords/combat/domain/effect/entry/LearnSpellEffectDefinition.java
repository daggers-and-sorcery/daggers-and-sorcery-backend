package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.SpellLearningService;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;

@Service
public class LearnSpellEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private SpellDefinitionCache spellDefinitionCache;

    @Autowired
    private SpellLearningService spellLearningService;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final int spellItemId = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("spell-item-id").getValue());
        final int spellId = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("spell-id").getValue());

        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();
        final SpellDefinition spellDefinition = spellDefinitionCache.getSpellDefinition(spellId);

        if(!spellLearningService.hasSpellLearned(userEntity, spellDefinition)) {
            spellLearningService.learnSpell(userEntity, spellDefinition);

            inventoryFacade.getInventory(userEntity).removeItem(spellItemId, 1);
        }
    }

    @Override
    public String getId() {
        return "learn_spell";
    }
}
