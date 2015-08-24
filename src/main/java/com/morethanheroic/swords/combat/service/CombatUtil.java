package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.skill.service.calc.SkillTypeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class CombatUtil {

    private final SkillManager skillManager;
    private final SkillTypeCalculator skillTypeCalculator;
    private final EquipmentManager equipmentManager;

    public CombatUtil(SkillManager skillManager, SkillTypeCalculator skillTypeCalculator, EquipmentManager equipmentManager) {
        this.skillManager = skillManager;
        this.skillTypeCalculator = skillTypeCalculator;
        this.equipmentManager = equipmentManager;
    }

    public int getUserWeaponSkillLevel(UserEntity user) {
        return skillManager.getSkills(user).getSkillLevel(getUserWeaponSkillType(user));
    }

    public int getUserArmorSkillLevel(UserEntity user) {
        return skillManager.getSkills(user).getSkillLevel(getUserArmorSkillType(user));
    }

    public SkillAttribute getUserArmorSkillType(UserEntity user) {
        return skillTypeCalculator.getSkillFromItemType(getUserArmorType(user));
    }

    public SkillAttribute getUserWeaponSkillType(UserEntity user) {
        return skillTypeCalculator.getSkillFromItemType(getUserWeaponType(user));
    }

    private ItemType getUserWeaponType(UserEntity user) {
        return equipmentManager.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON).getType();
    }

    private ItemType getUserArmorType(UserEntity user) {
        return equipmentManager.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.CHEST).getType();
    }
}
