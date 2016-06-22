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

    public int getUserWeaponSkillLevel(UserEntity user) {
        SkillType skill = getUserWeaponSkillType(user);

        if (skill == null) {
            return skillFacade.getSkills(user).getLevel(SkillType.FISTFIGHT);
        }

        return skillFacade.getSkills(user).getLevel(getUserWeaponSkillType(user));
    }

    public int getUserArmorSkillLevel(UserEntity user) {
        SkillType skill = getUserArmorSkillType(user);

        if (skill == null) {
            return skillFacade.getSkills(user).getLevel(SkillType.ARMORLESS_DEFENSE);
        }

        return skillFacade.getSkills(user).getLevel(getUserArmorSkillType(user));
    }

    public SkillType getUserArmorSkillType(UserEntity user) {
        ItemType itemType = getUserArmorType(user);

        if (itemType == null) {
            return null;
        }

        return skillTypeCalculator.getSkillFromItemType(itemType);
    }

    public SkillType getUserWeaponSkillType(UserEntity user) {
        ItemType itemType = getUserWeaponType(user);

        if (itemType == null) {
            return null;
        }

        return skillTypeCalculator.getSkillFromItemType(itemType);
    }

    public ItemType getUserWeaponType(UserEntity user) {
        ItemDefinition itemDefinition = equipmentFacade.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON);

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

    public boolean hasShield(final UserEntity userEntity) {
        final ItemDefinition offhandItemDefinition = equipmentFacade.getEquipment(userEntity).getEquipmentDefinitionOnSlot(EquipmentSlot.OFFHAND);

        return offhandItemDefinition != null && offhandItemDefinition.getType() == ItemType.SHIELD;
    }
}
