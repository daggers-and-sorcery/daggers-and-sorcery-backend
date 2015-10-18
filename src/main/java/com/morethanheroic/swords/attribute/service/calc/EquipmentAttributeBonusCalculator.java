package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.modifier.AttributeModifierDefinition;
import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.EquipmentBonusHolder;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.loader.domain.RawAttributeModifierDefinition;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class EquipmentAttributeBonusCalculator {

    private final EquipmentManager equipmentManager;
    private final ItemDefinitionManager itemDefinitionManager;

    @Autowired
    public EquipmentAttributeBonusCalculator(EquipmentManager equipmentManager, ItemDefinitionManager itemDefinitionManager) {
        this.equipmentManager = equipmentManager;
        this.itemDefinitionManager = itemDefinitionManager;
    }

    public int calculateEquipmentBonus(UserEntity userEntity, Attribute attribute) {
        int result = 0;

        EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for(EquipmentSlot slot : EquipmentSlot.values()) {
            int item = equipmentEntity.getEquipmentIdOnSlot(slot);

            if(item != 0) {
                ItemDefinition itemDefinition = itemDefinitionManager.getItemDefinition(equipmentEntity.getEquipmentIdOnSlot(slot));

                for (AttributeModifierDefinition modifierDefinition : itemDefinition.getAllModifiers()) {
                    if (modifierDefinition.getAttribute() == attribute) {
                        result += modifierDefinition.getAmount();
                    }
                }
            }
        }

        return result;
    }

    public EquipmentBonusHolder calculateEquipmentBonuses(UserEntity userEntity) {
        EquipmentBonusHolder attributeBonuses = new EquipmentBonusHolder();

        EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for(EquipmentSlot slot : EquipmentSlot.values()) {
            ItemDefinition itemDefinition = itemDefinitionManager.getItemDefinition(equipmentEntity.getEquipmentIdOnSlot(slot));

            for(AttributeModifierDefinition modifierDefinition : itemDefinition.getAllModifiers()) {
                attributeBonuses.addEquipmentBonus(modifierDefinition.getAttribute(), modifierDefinition.getAmount());
            }
        }

        return attributeBonuses;
    }
}
