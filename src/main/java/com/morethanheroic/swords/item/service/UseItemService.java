package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.combat.domain.effect.HealCombatEffect;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseItemService {

    private final CombatEffectApplierService combatEffectApplierService;
    private final UserManager userManager;

    @Autowired
    public UseItemService(CombatEffectApplierService combatEffectApplierService, UserManager userManager) {
        this.combatEffectApplierService = combatEffectApplierService;
        this.userManager = userManager;
    }

    public void useItem(UserEntity userEntity, int itemId) {
        UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity);

        //TODO: dont apply heal effect but rather get the effect of the item
        combatEffectApplierService.applyEffect(userCombatEntity, new HealCombatEffect(10));

        userManager.saveUser(userEntity);
    }
}
