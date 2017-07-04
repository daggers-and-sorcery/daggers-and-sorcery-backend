package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.DiceRollFromDamageSettingsBuilder;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoneSpikeSpellEffectDefinition extends ImprovedCombatEffectDefinition {

    private static final int AWARDED_DESTRUCTION_XP = 25;

    private static final int BONE_ID = 164;

    private final DiceRollCalculator diceRollCalculator;
    private final CombatMessageFactory combatMessageFactory;
    private final CombatCalculator combatCalculator;
    private final DiceRollFromDamageSettingsBuilder diceRollFromDamageSettingsBuilder;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        final DiceRollCalculationContext diceRollCalculationContext = diceRollFromDamageSettingsBuilder.buildDiceRollCalculationContext(effectApplyingContext.getEffectSettings());

        final int damage = diceRollCalculator.rollDices(diceRollCalculationContext);

        if (effectApplyingContext.getSource().isUser()) {
            final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity();
            final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

            if (!inventoryEntity.hasItem(itemDefinitionCache.getDefinition(BONE_ID))) {
                effectApplyingContext.addCombatStep(
                        DefaultCombatStep.builder()
                                .message(combatMessageFactory.newMessage("spell", "REQUIRED_ITEM_BONE_MISSING", damage))
                                .build()
                );

                return;
            }

            inventoryEntity.removeItem(itemDefinitionCache.getDefinition(BONE_ID), 1);

            effectApplyingContext.addCombatStep(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_done", "BONE_SPIKE_SPELL_DAMAGE_DONE", damage))
                            .build()
            );

            effectApplyingContext.getDestination().getCombatEntity().decreaseActualHealth(damage);

            combatCalculator.addCombatExperience(userEntity, SkillType.DESTRUCTION, AWARDED_DESTRUCTION_XP);
        } else {
            //TODO: Enable monsters to use this spell.

            throw new IllegalArgumentException("Caster as a monster is not supported for fireball.");
        }
    }

    @Override
    public String getId() {
        return "bone_spike_spell";
    }
}
