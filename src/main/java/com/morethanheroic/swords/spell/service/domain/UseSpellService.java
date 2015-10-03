package com.morethanheroic.swords.spell.service.domain;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseSpellService {

    private final CombatEffectApplierService combatEffectApplierService;
    private final InventoryManager inventoryManager;
    private final UserMapper userMapper;

    @Autowired
    public UseSpellService(CombatEffectApplierService combatEffectApplierService, InventoryManager inventoryManager, UserMapper userMapper) {
        this.combatEffectApplierService = combatEffectApplierService;
        this.inventoryManager = inventoryManager;
        this.userMapper = userMapper;
    }

    public boolean canUseSpell(UserEntity userEntity, SpellDefinition spell) {
        InventoryEntity inventoryEntity = inventoryManager.getInventory(userEntity);

        for (SpellCost spellCost : spell.getSpellCosts()) {
            if (spellCost.getType() == CostType.ITEM) {
                if (!inventoryEntity.hasItemAmount(spellCost.getId(), spellCost.getAmount())) {
                    return false;
                }
            } else if (spellCost.getType() == CostType.MANA) {
                if (userEntity.getMana() < spellCost.getAmount()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canUseSpell(UserCombatEntity combatEntity, SpellDefinition spell) {
        InventoryEntity inventoryEntity = inventoryManager.getInventory(combatEntity.getUserEntity());

        for (SpellCost spellCost : spell.getSpellCosts()) {
            if (spellCost.getType() == CostType.ITEM) {
                if (!inventoryEntity.hasItemAmount(spellCost.getId(), spellCost.getAmount())) {
                    return false;
                }
            } else if (spellCost.getType() == CostType.MANA) {
                if (combatEntity.getActualMana() < spellCost.getAmount()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void useSpell(UserEntity userEntity, SpellDefinition spell) {
        if (canUseSpell(userEntity, spell)) {
            applySpell(userEntity, spell);
        }
    }

    public void useSpell(UserCombatEntity combatEntity, SpellDefinition spell) {
        if (canUseSpell(combatEntity, spell)) {
            applySpell(combatEntity, spell);
        }
    }

    private void applySpell(CombatEntity userCombatEntity, SpellDefinition spell) {
        combatEffectApplierService.applyEffects(userCombatEntity, spell.getCombatEffects());
    }

    private void applySpell(UserEntity userEntity, SpellDefinition spell) {
        UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity);

        combatEffectApplierService.applyEffects(userCombatEntity, spell.getCombatEffects());

        userMapper.updateBasicCombatStats(userEntity.getId(), userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovement());
    }
}
