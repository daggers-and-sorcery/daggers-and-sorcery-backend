package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.combat.domain.effect.HealCombatEffect;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseItemService {

    private final CombatEffectApplierService combatEffectApplierService;
    private final UserManager userManager;
    private final InventoryManager inventoryManager;

    @Autowired
    public UseItemService(CombatEffectApplierService combatEffectApplierService, UserManager userManager, InventoryManager inventoryManager) {
        this.combatEffectApplierService = combatEffectApplierService;
        this.userManager = userManager;
        this.inventoryManager = inventoryManager;
    }

    public boolean canUseItem(UserEntity userEntity, int itemId) {
        return inventoryManager.getInventory(userEntity).hasItem(itemId);
    }

    public void useItem(UserEntity userEntity, int itemId) {
        if (canUseItem(userEntity, itemId)) {
            applyItem(userEntity, itemId);
        }
    }

    private void applyItem(UserEntity userEntity, int itemId) {
        //TODO: dont apply heal effect but rather get the effect of the item
        combatEffectApplierService.applyEffect(new UserCombatEntity(userEntity), new HealCombatEffect(10));

        userManager.saveUser(userEntity);
    }
}
