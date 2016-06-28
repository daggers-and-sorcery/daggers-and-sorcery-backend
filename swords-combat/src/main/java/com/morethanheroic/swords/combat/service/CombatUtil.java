package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.WeaponSuperType;
import com.morethanheroic.swords.item.service.WeaponSuperTypeCalculator;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CombatUtil {

    private final SkillFacade skillFacade;
    private final SkillTypeCalculator skillTypeCalculator;
    private final EquipmentFacade equipmentFacade;
    private final WeaponSuperTypeCalculator weaponSuperTypeCalculator;

    @Autowired
    public CombatUtil(SkillFacade skillFacade, SkillTypeCalculator skillTypeCalculator, EquipmentFacade equipmentFacade, WeaponSuperTypeCalculator weaponSuperTypeCalculator) {
        this.skillFacade = skillFacade;
        this.skillTypeCalculator = skillTypeCalculator;
        this.equipmentFacade = equipmentFacade;
        this.weaponSuperTypeCalculator = weaponSuperTypeCalculator;
    }

    public SkillType getUserArmorSkillType(UserEntity user) {
        final ItemType itemType = getUserArmorType(user);

        if (itemType == null) {
            return null;
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

        return itemDefinition.getType();
    }

    public Optional<WeaponSuperType> getUserWeaponSuperType(final UserEntity userEntity) {
        return weaponSuperTypeCalculator.calculateWeaponSuperType(getUserWeaponType(userEntity));
    }

    public ItemType getUserArmorType(UserEntity user) {
        final ItemDefinition itemDefinition = equipmentFacade.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.CHEST);

        if (itemDefinition == null) {
            return null;
        }

        return itemDefinition.getType();
    }

    public Optional<ItemType> getUserOffhandType(final UserEntity userEntity) {
        final ItemDefinition itemDefinition = equipmentFacade.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON);

        if (itemDefinition == null) {
            return Optional.empty();
        }

        return Optional.of(itemDefinition.getType());
    }

    public Optional<SkillType> getUserOffhandSkillType(final UserEntity userEntity) {
        final Optional<ItemType> itemType = getUserOffhandType(userEntity);

        if (!itemType.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(skillTypeCalculator.getSkillFromItemType(itemType.get()));
    }

    public boolean hasShield(final UserEntity userEntity) {
        final Optional<ItemType> itemType = getUserOffhandType(userEntity);

        return itemType.isPresent() && itemType.get() == ItemType.SHIELD;
    }
}
