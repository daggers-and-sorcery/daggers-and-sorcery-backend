package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.combat.domain.effect.HealCombatEffect;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseItemService {

    private final CombatEffectApplierService combatEffectApplierService;
    private final UserManager userManager;
    private final InventoryManager inventoryManager;
    private final UserMapper userMapper;

    @Autowired
    public UseItemService(CombatEffectApplierService combatEffectApplierService, UserManager userManager, InventoryManager inventoryManager, UserMapper userMapper) {
        this.combatEffectApplierService = combatEffectApplierService;
        this.userManager = userManager;
        this.inventoryManager = inventoryManager;
        this.userMapper = userMapper;
    }

    public boolean canUseItem(UserEntity userEntity, ItemDefinition item) {
        return inventoryManager.getInventory(userEntity).hasItem(item.getId());
    }

    public void useItem(UserEntity userEntity, ItemDefinition item) {
        if (canUseItem(userEntity, item)) {
            applyItem(userEntity, item);
        }
    }

    private void applyItem(UserEntity userEntity, ItemDefinition item) {
        UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity);

        combatEffectApplierService.applyEffects(userCombatEntity, item.getCombatEffects());

        userMapper.updateBasicCombatStats(userEntity.getId(), userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovement());
    }
}
