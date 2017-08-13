package com.morethanheroic.swords.spell.service;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.spell.domain.CostType;
import com.morethanheroic.swords.spell.domain.SpellCost;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.domain.SpellTarget;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//TODO: make monsters able to use combat spells too!
@Service
@RequiredArgsConstructor
public class UseSpellService {

    private final CombatEffectApplierService combatEffectApplierService;
    private final UserMapper userMapper;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatMessageFactory combatMessageFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    public boolean canUseSpell(UserEntity userEntity, SpellDefinition spell) {
        InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

        for (SpellCost rawSpellCost : spell.getSpellCosts()) {
            if (rawSpellCost.getType() == CostType.ITEM) {
                if (!inventoryEntity.hasItemAmount(rawSpellCost.getId(), rawSpellCost.getAmount(), true)) {
                    return false;
                }
            } else if (rawSpellCost.getType() == CostType.MANA) {
                if (userEntity.getManaPoints() < rawSpellCost.getAmount()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canUseSpell(UserCombatEntity combatEntity, SpellDefinition spell, List<CombatStep> combatSteps) {
        InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(combatEntity.getUserEntity());

        for (SpellCost rawSpellCost : spell.getSpellCosts()) {
            if (rawSpellCost.getType() == CostType.ITEM) {
                final ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(rawSpellCost.getId());

                if (!inventoryEntity.hasItemAmount(itemDefinition, rawSpellCost.getAmount(), IdentificationType.IDENTIFIED)) {
                    combatSteps.add(
                            DefaultCombatStep.builder()
                                    .message(combatMessageFactory.newMessage("spell", "COMBAT_MESSAGE_SPELL_CAST_NOT_ENOUGH_ITEM", itemDefinition.getName()))
                                    .build()
                    );

                    return false;
                }
            } else if (rawSpellCost.getType() == CostType.MANA) {
                if (combatEntity.getActualMana() < rawSpellCost.getAmount()) {
                    combatSteps.add(
                            DefaultCombatStep.builder()
                                    .message(combatMessageFactory.newMessage("spell", "COMBAT_MESSAGE_SPELL_CAST_NOT_ENOUGH_MANA"))
                                    .build()
                    );

                    return false;
                }
            }
        }

        return true;
    }

    public void useSpell(UserEntity userEntity, SpellDefinition spell, CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseSpell(userEntity, spell)) {
            applySpell(userEntity, spell, combatEffectDataHolder);
        }
    }

    //TODO: move combat stuff out of this
    public List<CombatStep> useSpell(CombatContext combatContext, SpellDefinition spell, CombatEffectDataHolder combatEffectDataHolder) {
        final List<CombatStep> combatSteps = new ArrayList<>();

        if (canUseSpell(combatContext.getUser(), spell, combatSteps)) {
            combatSteps.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("spell", "COMBAT_MESSAGE_SPELL_CAST", spell.getName()))
                            .build()
            );

            combatSteps.addAll(applySpell(combatContext, spell, combatEffectDataHolder));
        }

        final UserCombatEntity combatEntity = combatContext.getUser();
        combatEntity.getUserEntity().setBasicStats(combatEntity.getActualHealth(), combatEntity.getActualMana(), combatEntity.getUserEntity().getMovementPoints());

        return combatSteps;
    }

    //TODO: somehow merge the two usespell together?
    private List<CombatStep> applySpell(CombatContext combatContext, SpellDefinition spellDefinition, CombatEffectDataHolder combatEffectDataHolder) {
        final List<CombatStep> combatSteps = new ArrayList<>();

        final UserCombatEntity combatEntity = combatContext.getUser();

        for (SpellCost spellCost : spellDefinition.getSpellCosts()) {
            if (spellCost.getType() == CostType.ITEM) {
                final UserEntity userEntity = combatEntity.getUserEntity();

                inventoryEntityFactory.getEntity(userEntity).removeItem(spellCost.getId(), spellCost.getAmount());
            } else if (spellCost.getType() == CostType.MANA) {
                combatEntity.decreaseActualMana(spellCost.getAmount());
            }
        }

        if (spellDefinition.getSpellTarget() == SpellTarget.SELF) {
            final List<CombatEffectApplyingContext> contexts = new ArrayList<>();
            for (EffectSettingDefinitionHolder effectSettingDefinitionHolder : spellDefinition.getCombatEffects()) {
                contexts.add(CombatEffectApplyingContext.builder()
                        .source(new CombatEffectTarget(combatContext.getUser()))
                        .destination(new CombatEffectTarget(combatContext.getUser()))
                        .combatSteps(combatSteps)
                        .effectSettings(effectSettingDefinitionHolder)
                        .build()
                );
            }

            combatEffectApplierService.applyEffects(contexts, combatEffectDataHolder);
        } else {
            //Here the target is the monster. This should be however refactored somehow.
            final List<CombatEffectApplyingContext> contexts = new ArrayList<>();
            for (EffectSettingDefinitionHolder effectSettingDefinitionHolder : spellDefinition.getCombatEffects()) {
                contexts.add(CombatEffectApplyingContext.builder()
                        .source(new CombatEffectTarget(combatContext.getUser()))
                        .destination(new CombatEffectTarget(combatContext.getOpponent()))
                        .combatSteps(combatSteps)
                        .effectSettings(effectSettingDefinitionHolder)
                        .build()
                );
            }

            combatEffectApplierService.applyEffects(contexts, combatEffectDataHolder);
        }

        return combatSteps;
    }

    private void applySpell(UserEntity userEntity, SpellDefinition spellDefinition, CombatEffectDataHolder combatEffectDataHolder) {
        final UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);

        final List<CombatEffectApplyingContext> contexts = new ArrayList<>();

        for (SpellCost spellCost : spellDefinition.getSpellCosts()) {
            if (spellCost.getType() == CostType.ITEM) {
                inventoryEntityFactory.getEntity(userEntity).removeItem(spellCost.getId(), spellCost.getAmount());
            } else if (spellCost.getType() == CostType.MANA) {
                userCombatEntity.decreaseActualMana(spellCost.getAmount());
            }
        }

        for (EffectSettingDefinitionHolder effectSettingDefinitionHolder : spellDefinition.getCombatEffects()) {
            contexts.add(
                    CombatEffectApplyingContext.builder()
                            .source(new CombatEffectTarget(userCombatEntity))
                            .destination(new CombatEffectTarget(userCombatEntity))
                            .combatSteps(Lists.newArrayList())
                            .effectSettings(effectSettingDefinitionHolder)
                            .build()
            );
        }

        combatEffectApplierService.applyEffects(contexts, combatEffectDataHolder);

        userMapper.updateBasicCombatStats(userEntity.getId(), userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints());
    }
}
