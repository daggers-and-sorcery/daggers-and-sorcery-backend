package com.morethanheroic.swords.attribute.service;

import com.morethanheroic.swords.attribute.domain.*;
import com.morethanheroic.swords.item.domain.modifier.ItemModifier;
import org.springframework.stereotype.Service;

@Service
public class ItemModifierToAttributeConverter {

    public Attribute convert(ItemModifier itemModifier) {
        switch (itemModifier) {
            case MOVEMENT:
                return BasicAttribute.MOVEMENT;
            case MANA:
                return CombatAttribute.MANA;
            case LIFE:
                return CombatAttribute.LIFE;
            case INITIATION:
                return CombatAttribute.INITIATION;
            case ATTACK:
                return CombatAttribute.ATTACK;
            case MAGIC_ATTACK:
                return CombatAttribute.MAGIC_ATTACK;
            case MAGIC_DAMAGE:
                return CombatAttribute.MAGIC_DAMAGE;
            case AIMING:
                return CombatAttribute.AIMING;
            case DEFENSE:
                return CombatAttribute.DEFENSE;
            case DAMAGE_REDUCTION:
                return CombatAttribute.DAMAGE_REDUCTION;
            case SPELL_RESISTANCE:
                return CombatAttribute.SPELL_RESISTANCE;
            case DAMAGE:
                return CombatAttribute.DAMAGE;
            case RANGED_DAMAGE:
                return CombatAttribute.RANGED_DAMAGE;
            case STRENGTH:
                return GeneralAttribute.STRENGTH;
            case PERCEPTION:
                return GeneralAttribute.PERCEPTION;
            case DEXTERITY:
                return GeneralAttribute.DEXTERITY;
            case SWIFTNESS:
                return GeneralAttribute.SWIFTNESS;
            case VITALITY:
                return GeneralAttribute.VITALITY;
            case ENDURANCE:
                return GeneralAttribute.ENDURANCE;
            case BEAUTY:
                return GeneralAttribute.BEAUTY;
            case INTELLIGENCE:
                return GeneralAttribute.INTELLIGENCE;
            case WISDOM:
                return GeneralAttribute.WISDOM;
            case WILLPOWER:
                return GeneralAttribute.WILLPOWER;
            case CHARISMA:
                return GeneralAttribute.CHARISMA;
            case SCAVENGING_BONUS:
                return BasicAttribute.SCAVENGING_BONUS;
            case TWO_HANDED_CRUSHING_WEAPONS:
                return SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS;
            case ONE_HANDED_CRUSHING_WEAPONS:
                return SkillAttribute.ONE_HANDED_CRUSHING_WEAPONS;
            case TWO_HANDED_AXES:
                return SkillAttribute.TWO_HANDED_AXES;
            case ONE_HANDED_AXES:
                return SkillAttribute.ONE_HANDED_AXES;
            case THROWING_WEAPONS:
                return SkillAttribute.THROWING_WEAPONS;
            case FISTFIGHT:
                return SkillAttribute.FISTFIGHT;
            case LONGSWORDS:
                return SkillAttribute.LONGSWORDS;
            case SHORTSWORDS:
                return SkillAttribute.SHORTSWORDS;
            case POLEARMS:
                return SkillAttribute.POLEARMS;
            case DAGGERS:
                return SkillAttribute.DAGGERS;
            case LONGBOWS:
                return SkillAttribute.LONGBOWS;
            case SHORTBOWS:
                return SkillAttribute.SHORTBOWS;
            case CROSSBOWS:
                return SkillAttribute.CROSSBOWS;
            case LIGHT_ARMOR:
                return SkillAttribute.LIGHT_ARMOR;
            case HEAVY_ARMOR:
                return SkillAttribute.HEAVY_ARMOR;
            case ROBE_ARMOR:
                return SkillAttribute.ROBE_ARMOR;
            case ARMORLESS_DEFENSE:
                return SkillAttribute.ARMORLESS_DEFENSE;
            case SHIELD_DEFENSE:
                return SkillAttribute.SHIELD_DEFENSE;
            case STAFF:
                return SkillAttribute.STAFF;
            case WAND:
                return SkillAttribute.WAND;
            case SCEPTRE:
                return SkillAttribute.SCEPTRE;
            case SCAVENGING:
                return SkillAttribute.SCAVENGING;
            case COOKING:
                return SkillAttribute.COOKING;
                //Special
            case LIFE_STEAL:
                return SpecialAttribute.LIFE_STEAL;
            case EXTRA_DAMAGE_AGAINST_VAMPIRES:
                return SpecialAttribute.EXTRA_DAMAGE_AGAINST_VAMPIRES;
            case PERIODICAL_DAMAGE_AGAINST_VAMPIRES:
                return SpecialAttribute.PERIODICAL_DAMAGE_AGAINST_VAMPIRES;
            case PERIODICAL_DAMAGE_AGAINST_UNDEAD:
                return SpecialAttribute.PERIODICAL_DAMAGE_AGAINST_UNDEAD;
            default:
                throw new IllegalArgumentException("Item modifier cannot be converted to attribute");
        }
    }
}
