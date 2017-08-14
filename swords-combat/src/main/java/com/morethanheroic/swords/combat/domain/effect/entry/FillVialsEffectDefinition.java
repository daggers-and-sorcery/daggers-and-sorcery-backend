package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FillVialsEffectDefinition extends ImprovedCombatEffectDefinition {

    private static final int VIAL_ID = 18;
    private static final int VIAL_OF_WATER_ID = 189;
    private static final int MAX_VIALS_TO_TRANSFORM = 5;

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;

    @Override
    public void apply(final CombatEffectApplyingContext effectApplyingContext) {
        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity();

        if (globalAttributeCalculator.calculateActualValue(userEntity, BasicAttribute.MOVEMENT).getValue() > 0) {
            final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

            final int vialAmountInInventory = inventoryEntity.getItemAmount(itemDefinitionCache.getDefinition(VIAL_ID));
            final int vialsToTransform = vialAmountInInventory > MAX_VIALS_TO_TRANSFORM ? MAX_VIALS_TO_TRANSFORM : vialAmountInInventory;

            inventoryEntity.removeItem(itemDefinitionCache.getDefinition(VIAL_ID), vialsToTransform);
            inventoryEntity.addItem(itemDefinitionCache.getDefinition(VIAL_OF_WATER_ID), vialsToTransform);

            userBasicAttributeManipulator.decreaseMovement(userEntity, 1);
        }
    }

    @Override
    public String getId() {
        return "fill_vials";
    }
}
