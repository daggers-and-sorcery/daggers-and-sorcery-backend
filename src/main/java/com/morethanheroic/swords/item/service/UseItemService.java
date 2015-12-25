package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffect;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseItemService {

    private final CombatEffectApplierService combatEffectApplierService;
    private final InventoryFacade inventoryFacade;
    private final UserMapper userMapper;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public UseItemService(CombatEffectApplierService combatEffectApplierService, InventoryFacade inventoryFacade, UserMapper userMapper) {
        this.combatEffectApplierService = combatEffectApplierService;
        this.inventoryFacade = inventoryFacade;
        this.userMapper = userMapper;
    }

    public boolean canUseItem(UserEntity userEntity, ItemDefinition item) {
        return inventoryFacade.getInventory(userEntity).hasItem(item.getId());
    }

    public void useItem(UserCombatEntity combatEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseItem(combatEntity.getUserEntity(), item)) {
            applyItem(combatEntity, item, combatEffectDataHolder);
        }
    }

    public void useItem(UserEntity userEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseItem(userEntity, item)) {
            applyItem(userEntity, item, combatEffectDataHolder);
        }
    }

    private void applyItem(CombatEntity userCombatEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        combatEffectApplierService.applyEffects(userCombatEntity, (List) item.getCombatEffects(), combatEffectDataHolder);
    }

    private void applyItem(UserEntity userEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);

        combatEffectApplierService.applyEffects(userCombatEntity, (List) item.getCombatEffects(), combatEffectDataHolder);

        userMapper.updateBasicCombatStats(userEntity.getId(), userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints());
    }
}
