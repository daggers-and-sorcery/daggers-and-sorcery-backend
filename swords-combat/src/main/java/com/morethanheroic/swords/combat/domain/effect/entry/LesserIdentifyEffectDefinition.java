package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LesserIdentifyEffectDefinition extends CombatEffectDefinition {

    private static final int ALTERATION_EXPERIENCE_REWARD = 150;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();
        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        final int realItem = unidentifiedItemIdCalculator.getRealItemId(combatEffectDataHolder.getSessionEntity(), Integer.parseInt((String) combatEffectDataHolder.getParameters().get("itemId")));

        if (inventoryEntity.hasItem(realItem, false)) {
            inventoryEntity.removeItem(realItem, 1, false);
            inventoryEntity.addItem(realItem, 1, true);
        }

        skillEntityFactory.getSkillEntity(userEntity).increaseExperience(SkillType.ALTERATION, ALTERATION_EXPERIENCE_REWARD);
    }

    @Override
    public String getId() {
        return "lesser_identify";
    }
}
