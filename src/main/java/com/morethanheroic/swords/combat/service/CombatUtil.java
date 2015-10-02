package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.skill.service.calc.SkillTypeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatUtil {

    private final SkillManager skillManager;
    private final SkillTypeCalculator skillTypeCalculator;
    private final EquipmentManager equipmentManager;

    @Autowired
    public CombatUtil(SkillManager skillManager, SkillTypeCalculator skillTypeCalculator, EquipmentManager equipmentManager) {
        this.skillManager = skillManager;
        this.skillTypeCalculator = skillTypeCalculator;
        this.equipmentManager = equipmentManager;
    }

    public int getUserWeaponSkillLevel(UserEntity user) {
        SkillAttribute skill = getUserWeaponSkillType(user);

        if(skill == null) {
            return skillManager.getSkills(user).getSkillLevel(SkillAttribute.FISTFIGHT);
        }

        return skillManager.getSkills(user).getSkillLevel(getUserWeaponSkillType(user));
    }

    public int getUserArmorSkillLevel(UserEntity user) {
        SkillAttribute skill = getUserArmorSkillType(user);

        if(skill == null) {
            return skillManager.getSkills(user).getSkillLevel(SkillAttribute.ARMORLESS_DEFENSE);
        }

        return skillManager.getSkills(user).getSkillLevel(getUserArmorSkillType(user));
    }

    public SkillAttribute getUserArmorSkillType(UserEntity user) {
        ItemType itemType = getUserArmorType(user);

        if(itemType == null) {
            return null;
        }

        return skillTypeCalculator.getSkillFromItemType(itemType);
    }

    public SkillAttribute getUserWeaponSkillType(UserEntity user) {
        ItemType itemType = getUserWeaponType(user);

        if(itemType == null) {
            return null;
        }

        return skillTypeCalculator.getSkillFromItemType(itemType);
    }

    public ItemType getUserWeaponType(UserEntity user) {
        ItemDefinition itemDefinition = equipmentManager.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON);

        if(itemDefinition == null) {
            return null;
        }

        return itemDefinition.getType();
    }

    public ItemType getUserArmorType(UserEntity user) {
        ItemDefinition itemDefinition = equipmentManager.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.CHEST);

        if(itemDefinition == null) {
            return null;
        }

        return itemDefinition.getType();
    }
}
