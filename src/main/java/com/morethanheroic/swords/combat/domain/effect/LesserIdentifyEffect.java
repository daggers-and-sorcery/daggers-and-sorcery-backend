package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingHolder;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;

public class LesserIdentifyEffect extends CombatEffect {

    public LesserIdentifyEffect(EffectSettingHolder effectSettingHolder) {
        super(effectSettingHolder);
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        InventoryEntity inventoryEntity = combatEffectServiceAccessor.getInventoryFacade().getInventory(((UserCombatEntity) combatEntity).getUserEntity());

        int realItem = combatEffectServiceAccessor.getUnidentifiedItemIdCalculator().getRealItemId(combatEffectDataHolder.getHttpSession(), Integer.parseInt((String) combatEffectDataHolder.getParameters().get("itemId")));

        if (inventoryEntity.hasItem(realItem, false)) {
            inventoryEntity.removeItem(realItem, 1, false);
            inventoryEntity.addItem(realItem, 1, true);
        }
    }
}
