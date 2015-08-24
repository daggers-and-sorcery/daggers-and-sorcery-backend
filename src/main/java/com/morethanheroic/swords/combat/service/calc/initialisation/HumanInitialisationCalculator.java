package com.morethanheroic.swords.combat.service.calc.initialisation;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.skill.service.calc.SkillTypeCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HumanInitialisationCalculator {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final SkillTypeCalculator skillTypeCalculator;
    private final EquipmentManager equipmentManager;
    private final SkillManager skillManager;
    private final Random random;

    @Autowired
    public HumanInitialisationCalculator(GlobalAttributeCalculator globalAttributeCalculator, SkillTypeCalculator skillTypeCalculator, EquipmentManager equipmentManager, SkillManager skillManager, Random random) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.skillTypeCalculator = skillTypeCalculator;
        this.equipmentManager = equipmentManager;
        this.skillManager = skillManager;
        this.random = random;
    }

    public int calculateInitialisation(UserEntity user) {
        return globalAttributeCalculator.calculateActualValue(user, CombatAttribute.INITIATION) + random.nextInt(getUserWeaponSkillLevel(user));
    }

    private int getUserWeaponSkillLevel(UserEntity user) {
        return skillManager.getSkills(user).getSkillLevel(skillTypeCalculator.getSkillFromItemType(getUserWeaponType(user)));
    }

    private ItemType getUserWeaponType(UserEntity user) {
        return equipmentManager.getEquipment(user).getEquipmentDefinitionOnSlot(EquipmentSlot.WEAPON).getType();
    }
}
