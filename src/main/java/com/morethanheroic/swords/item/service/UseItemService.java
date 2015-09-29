package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.combat.domain.effect.HealCombatEffect;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseItemService {

    private final CombatEffectApplierService combatEffectApplierService;

    @Autowired
    public UseItemService(CombatEffectApplierService combatEffectApplierService) {
        this.combatEffectApplierService = combatEffectApplierService;
    }

    public void useItem(UserEntity userEntity, int itemId) {
        UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity);

        //TODO: dont apply heal effect but rather get the effect of the item
        combatEffectApplierService.applyEffect(userCombatEntity, new HealCombatEffect(10));
    }
}
