package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.domain.WeaponSuperType;
import com.morethanheroic.swords.item.service.WeaponSuperTypeCalculator;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CombatUtil {

    private static final int EMPTY_EQUIPMENT_SLOT = 0;

    private final SkillTypeCalculator skillTypeCalculator;
    private final EquipmentFacade equipmentFacade;
    private final WeaponSuperTypeCalculator weaponSuperTypeCalculator;

    public SkillType getUserArmorSkillType(UserEntity user) {
        final ItemType itemType = getUserArmorType(user);

        if (itemType == null) {
            return SkillType.ARMORLESS_DEFENSE;
        }

        return skillTypeCalculator.getSkillFromItemType(itemType);
    }

    public SkillType getUserWeaponSkillType(UserEntity user) {
        final ItemType itemType = getUserWeaponType(user);

        if (itemType == null) {
            return null;
        }

        return skillTypeCalculator.getSkillFromItemType(itemType);
    }

    public ItemType getUserWeaponType(UserEntity user) {
        final ItemDefinition itemDefinition = equipmentFacade.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON);

        if (itemDefinition == null) {
            return null;
        }

        return itemDefinition.getSubtype();
    }

    public Optional<WeaponSuperType> getUserWeaponSuperType(final UserEntity userEntity) {
        return weaponSuperTypeCalculator.calculateWeaponSuperType(getUserWeaponType(userEntity));
    }

    public ItemType getUserArmorType(UserEntity user) {
        final ItemDefinition itemDefinition = equipmentFacade.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.CHEST);

        if (itemDefinition == null) {
            return null;
        }

        return itemDefinition.getSubtype();
    }

    public Optional<ItemType> getUserOffhandType(final UserEntity userEntity) {
        final ItemDefinition itemDefinition = equipmentFacade.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.OFFHAND);

        if (itemDefinition == null) {
            return Optional.empty();
        }

        return Optional.of(itemDefinition.getSubtype());
    }

    public Optional<SkillType> getUserOffhandSkillType(final UserEntity userEntity) {
        return getUserOffhandType(userEntity)
                .map(skillTypeCalculator::getSkillFromItemType);

    }

    public boolean hasShield(final UserEntity userEntity) {
        final Optional<ItemType> itemType = getUserOffhandType(userEntity);

        return itemType.isPresent() && itemType.get() == ItemType.SHIELD;
    }

    public boolean isFistfighting(final UserEntity userEntity) {
        final EquipmentEntity equipmentEntity = equipmentFacade.getEquipment(userEntity);

        return equipmentEntity.getEquipmentIdOnSlot(EquipmentSlot.WEAPON) == EMPTY_EQUIPMENT_SLOT
                || (hasBowWeapon(equipmentEntity) && equipmentEntity.getEquipmentIdOnSlot(EquipmentSlot.QUIVER) == EMPTY_EQUIPMENT_SLOT);
    }

    public boolean hasBowWeapon(final EquipmentEntity equipmentEntity) {
        final ItemType weaponType = equipmentEntity.getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON).getSubtype();

        return weaponType == ItemType.SHORTBOWS || weaponType == ItemType.LONGBOWS || weaponType == ItemType.CROSSBOWS;
    }
}
