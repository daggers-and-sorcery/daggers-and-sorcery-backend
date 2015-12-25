package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatUtil {

    private final SkillFacade skillFacade;
    private final SkillTypeCalculator skillTypeCalculator;
    private final EquipmentManager equipmentManager;

    @Autowired
    public CombatUtil(SkillFacade skillFacade, SkillTypeCalculator skillTypeCalculator, EquipmentManager equipmentManager) {
        this.skillFacade = skillFacade;
        this.skillTypeCalculator = skillTypeCalculator;
        this.equipmentManager = equipmentManager;
    }

    public int getUserWeaponSkillLevel(UserEntity user) {
        SkillType skill = getUserWeaponSkillType(user);

        if(skill == null) {
            return skillFacade.getSkills(user).getSkillLevel(SkillType.FISTFIGHT);
        }

        return skillFacade.getSkills(user).getSkillLevel(getUserWeaponSkillType(user));
    }

    public int getUserArmorSkillLevel(UserEntity user) {
        SkillType skill = getUserArmorSkillType(user);

        if(skill == null) {
            return skillFacade.getSkills(user).getSkillLevel(SkillType.ARMORLESS_DEFENSE);
        }

        return skillFacade.getSkills(user).getSkillLevel(getUserArmorSkillType(user));
    }

    public SkillType getUserArmorSkillType(UserEntity user) {
        ItemType itemType = getUserArmorType(user);

        if(itemType == null) {
            return null;
        }

        return skillTypeCalculator.getSkillFromItemType(itemType);
    }

    public SkillType getUserWeaponSkillType(UserEntity user) {
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
