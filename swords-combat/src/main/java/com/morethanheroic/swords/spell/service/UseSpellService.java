package com.morethanheroic.swords.spell.service;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.spell.domain.CostType;
import com.morethanheroic.swords.spell.domain.SpellCost;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.domain.SpellTarget;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//TODO: make monsters able to use combat spells too!
public class UseSpellService {

    private final CombatEffectApplierService combatEffectApplierService;
    private final InventoryFacade inventoryFacade;
    private final UserMapper userMapper;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public UseSpellService(CombatEffectApplierService combatEffectApplierService, InventoryFacade inventoryFacade, UserMapper userMapper) {
        this.combatEffectApplierService = combatEffectApplierService;
        this.inventoryFacade = inventoryFacade;
        this.userMapper = userMapper;
    }

    public boolean canUseSpell(UserEntity userEntity, SpellDefinition spell) {
        InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

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

    public boolean canUseSpell(UserCombatEntity combatEntity, SpellDefinition spell) {
        InventoryEntity inventoryEntity = inventoryFacade.getInventory(combatEntity.getUserEntity());

        for (SpellCost rawSpellCost : spell.getSpellCosts()) {
            if (rawSpellCost.getType() == CostType.ITEM) {
                if (!inventoryEntity.hasItemAmount(rawSpellCost.getId(), rawSpellCost.getAmount(), true)) {
                    return false;
                }
            } else if (rawSpellCost.getType() == CostType.MANA) {
                if (combatEntity.getActualMana() < rawSpellCost.getAmount()) {
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

    public void useSpell(Combat combat, CombatResult combatResult, SpellDefinition spell, CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseSpell(combat.getUserCombatEntity(), spell)) {
            applySpell(combat, combatResult, spell, combatEffectDataHolder);
        } else {
            final CombatMessage combatMessage = new CombatMessage();

            combatMessage.addData("icon", "spell");
            combatMessage.addData("message", "Using the spell was failed! You don't have enough mana.");

            combatResult.addMessage(combatMessage);
        }
    }

    //TODO: somehow merge the two usespell together?
    private void applySpell(Combat combat, CombatResult combatResult, SpellDefinition spellDefinition, CombatEffectDataHolder combatEffectDataHolder) {
        final UserCombatEntity combatEntity = combat.getUserCombatEntity();

        for (SpellCost spellCost : spellDefinition.getSpellCosts()) {
            if (spellCost.getType() == CostType.ITEM) {
                final UserEntity userEntity = combatEntity.getUserEntity();

                inventoryFacade.getInventory(userEntity).removeItem(spellCost.getId(), spellCost.getAmount());
            } else if (spellCost.getType() == CostType.MANA) {
                combatEntity.decreaseActualMana(spellCost.getAmount());
            }
        }

        if (spellDefinition.getSpellTarget() == SpellTarget.SELF) {
            final CombatEffectApplyingContext effectApplyingContext = CombatEffectApplyingContext.builder()
                                                                                                 .source(new CombatEffectTarget(combat.getUserCombatEntity()))
                                                                                                 .destination(new CombatEffectTarget(combat.getUserCombatEntity()))
                                                                                                 .combatResult(combatResult)
                                                                                                 .build();

            combatEffectApplierService.applyEffects(effectApplyingContext, spellDefinition.getCombatEffects(), combatEffectDataHolder);
        } else {
            final CombatEffectApplyingContext effectApplyingContext = CombatEffectApplyingContext.builder()
                                                                                                 .source(new CombatEffectTarget(combat.getUserCombatEntity()))
                                                                                                 .destination(new CombatEffectTarget(combat.getMonsterCombatEntity()))
                                                                                                 .combatResult(combatResult)
                                                                                                 .build();

            combatEffectApplierService.applyEffects(effectApplyingContext, spellDefinition.getCombatEffects(), combatEffectDataHolder);
        }
    }

    private void applySpell(UserEntity userEntity, SpellDefinition spell, CombatEffectDataHolder combatEffectDataHolder) {
        final UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);
        final CombatResult combatResult = new CombatResult();
        final Combat combat = new Combat(userEntity, null, globalAttributeCalculator);
        final CombatEffectApplyingContext effectApplyingContext = CombatEffectApplyingContext.builder()
                                                                                             .source(new CombatEffectTarget(userCombatEntity))
                                                                                             .destination(new CombatEffectTarget(userCombatEntity))
                                                                                             .combatResult(combatResult)
                                                                                             .build();

        combatEffectApplierService.applyEffects(effectApplyingContext, spell.getCombatEffects(), combatEffectDataHolder);

        userMapper.updateBasicCombatStats(userEntity.getId(), userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints());
    }
}
