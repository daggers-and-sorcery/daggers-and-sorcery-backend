package com.morethanheroic.swords.skill.repository.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Contains mappings to sql queries to access skill data.
 */
@Repository
public interface SkillMapper {

    @Insert("INSERT INTO skills SET user_id = #{userId}")
    void insert(@Param("userId") int userId);

    @Select("SELECT * FROM skills WHERE user_id = #{userId}")
    SkillDatabaseEntity getSkills(@Param("userId") int userId);

    @Update("UPDATE skills SET two_handed_crushing_weapons_xp = two_handed_crushing_weapons_xp + #{value} WHERE user_id = #{userId}")
    void increaseTwoHandedCrushingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET two_handed_crushing_weapons_xp = two_handed_crushing_weapons_xp - #{value} WHERE user_id = #{userId}")
    void decreaseTwoHandedCrushingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET one_handed_crushing_weapons_xp = one_handed_crushing_weapons_xp + #{value} WHERE user_id = #{userId}")
    void increaseOneHandedCrushingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET one_handed_crushing_weapons_xp = one_handed_crushing_weapons_xp - #{value} WHERE user_id = #{userId}")
    void decreaseOneHandedCrushingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET two_handed_axes_xp = two_handed_axes_xp + #{value} WHERE user_id = #{userId}")
    void increaseTwoHandedAxesXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET two_handed_axes_xp = two_handed_axes_xp - #{value} WHERE user_id = #{userId}")
    void decreaseTwoHandedAxesXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET one_handed_axes_xp = one_handed_axes_xp + #{value} WHERE user_id = #{userId}")
    void increaseOneHandedAxesXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET one_handed_axes_xp = one_handed_axes_xp - #{value} WHERE user_id = #{userId}")
    void decreaseOneHandedAxesXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET throwing_weapons_xp = throwing_weapons_xp + #{value} WHERE user_id = #{userId}")
    void increaseThrowingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET throwing_weapons_xp = throwing_weapons_xp - #{value} WHERE user_id = #{userId}")
    void decreaseThrowingWeaponsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET fistfight_xp = fistfight_xp + #{value} WHERE user_id = #{userId}")
    void increaseFistfightXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET fistfight_xp = fistfight_xp - #{value} WHERE user_id = #{userId}")
    void decreaseFistfightXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET longswords_xp = longswords_xp + #{value} WHERE user_id = #{userId}")
    void increaseLongswordsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET longswords_xp = longswords_xp - #{value} WHERE user_id = #{userId}")
    void decreaseLongswordsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shortswords_xp = shortswords_xp + #{value} WHERE user_id = #{userId}")
    void increaseShortswordsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shortswords_xp = shortswords_xp - #{value} WHERE user_id = #{userId}")
    void decreaseShortswordsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET polearms_xp = polearms_xp + #{value} WHERE user_id = #{userId}")
    void increasePolearmsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET polearms_xp = polearms_xp - #{value} WHERE user_id = #{userId}")
    void decreasePolearmsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET daggers_xp = daggers_xp + #{value} WHERE user_id = #{userId}")
    void increaseDaggersXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET daggers_xp = daggers_xp - #{value} WHERE user_id = #{userId}")
    void decreaseDaggersXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET longbows_xp = longbows_xp + #{value} WHERE user_id = #{userId}")
    void increaseLongbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET longbows_xp = longbows_xp - #{value} WHERE user_id = #{userId}")
    void decreaseLongbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shortbows_xp = shortbows_xp + #{value} WHERE user_id = #{userId}")
    void increaseShortbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shortbows_xp = shortbows_xp - #{value} WHERE user_id = #{userId}")
    void decreaseShortbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET crossbows_xp = crossbows_xp + #{value} WHERE user_id = #{userId}")
    void increaseCrossbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET crossbows_xp = crossbows_xp - #{value} WHERE user_id = #{userId}")
    void decreaseCrossbowsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET light_armor_xp = light_armor_xp + #{value} WHERE user_id = #{userId}")
    void increaseLightArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET light_armor_xp = light_armor_xp - #{value} WHERE user_id = #{userId}")
    void decreaseLightArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET heavy_armor_xp = heavy_armor_xp + #{value} WHERE user_id = #{userId}")
    void increaseHeavyArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET heavy_armor_xp = heavy_armor_xp - #{value} WHERE user_id = #{userId}")
    void decreaseHeavyArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET robe_armor_xp = robe_armor_xp + #{value} WHERE user_id = #{userId}")
    void increaseRobeArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET robe_armor_xp = robe_armor_xp - #{value} WHERE user_id = #{userId}")
    void decreaseRobeArmorXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET armorless_defense_xp = armorless_defense_xp + #{value} WHERE user_id = #{userId}")
    void increaseArmorlessDefenseXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET armorless_defense_xp = armorless_defense_xp - #{value} WHERE user_id = #{userId}")
    void decreaseArmorlessDefenseXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shield_defense_xp = shield_defense_xp + #{value} WHERE user_id = #{userId}")
    void increaseShieldDefenseXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET shield_defense_xp = shield_defense_xp - #{value} WHERE user_id = #{userId}")
    void decreaseShieldDefenseXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET staffs_xp = staffs_xp + #{value} WHERE user_id = #{userId}")
    void increaseStaffsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET staffs_xp = staffs_xp - #{value} WHERE user_id = #{userId}")
    void decreaseStaffsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET wands_xp = wands_xp + #{value} WHERE user_id = #{userId}")
    void increaseWandsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET wands_xp = wands_xp - #{value} WHERE user_id = #{userId}")
    void decreaseWandsXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET sceptres_xp = sceptres_xp + #{value} WHERE user_id = #{userId}")
    void increaseSceptresXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET sceptres_xp = sceptres_xp - #{value} WHERE user_id = #{userId}")
    void decreaseSceptresXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET scavenging_xp = scavenging_xp + #{value} WHERE user_id = #{userId}")
    void increaseScavengingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET scavenging_xp = scavenging_xp - #{value} WHERE user_id = #{userId}")
    void decreaseScavengingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET cooking_xp = cooking_xp + #{value} WHERE user_id = #{userId}")
    void increaseCookingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET cooking_xp = cooking_xp - #{value} WHERE user_id = #{userId}")
    void decreaseCookingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET leatherworking_xp = leatherworking_xp + #{value} WHERE user_id = #{userId}")
    void increaseLeatherworkingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET leatherworking_xp = leatherworking_xp - #{value} WHERE user_id = #{userId}")
    void decreaseLeatherworkingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET smithing_xp = smithing_xp + #{value} WHERE user_id = #{userId}")
    void increaseSmithingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET smithing_xp = smithing_xp - #{value} WHERE user_id = #{userId}")
    void decreaseSmithingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET herblore_xp = herblore_xp + #{value} WHERE user_id = #{userId}")
    void increaseHerbloreXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET herblore_xp = herblore_xp - #{value} WHERE user_id = #{userId}")
    void decreaseHerbloreXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET focus_xp = focus_xp + #{value} WHERE user_id = #{userId}")
    void increaseFocusXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET focus_xp = focus_xp - #{value} WHERE user_id = #{userId}")
    void decreaseFocusXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET alteration_xp = alteration_xp + #{value} WHERE user_id = #{userId}")
    void increaseAlterationXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET alteration_xp = alteration_xp - #{value} WHERE user_id = #{userId}")
    void decreaseAlterationXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET destruction_xp = destruction_xp + #{value} WHERE user_id = #{userId}")
    void increaseDestructionXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET destruction_xp = destruction_xp - #{value} WHERE user_id = #{userId}")
    void decreaseDestructionXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET restoration_xp = restoration_xp + #{value} WHERE user_id = #{userId}")
    void increaseRestorationXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET restoration_xp = restoration_xp - #{value} WHERE user_id = #{userId}")
    void decreaseRestorationXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET lockpicking_xp = lockpicking_xp + #{value} WHERE user_id = #{userId}")
    void increaseLockpickingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET lockpicking_xp = lockpicking_xp - #{value} WHERE user_id = #{userId}")
    void decreaseLockpickingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET fletching_xp = fletching_xp + #{value} WHERE user_id = #{userId}")
    void increaseFletchingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET fletching_xp = fletching_xp - #{value} WHERE user_id = #{userId}")
    void decreaseFletchingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET jewelcrafting_xp = jewelcrafting_xp + #{value} WHERE user_id = #{userId}")
    void increaseJewelcraftingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET jewelcrafting_xp = jewelcrafting_xp - #{value} WHERE user_id = #{userId}")
    void decreaseJewelcraftingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET crafting_xp = crafting_xp + #{value} WHERE user_id = #{userId}")
    void increaseCraftingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET crafting_xp = crafting_xp - #{value} WHERE user_id = #{userId}")
    void decreaseCraftingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET imbuing_xp = imbuing_xp + #{value} WHERE user_id = #{userId}")
    void increaseImbuingXp(@Param("userId") int userId, @Param("value") long value);

    @Update("UPDATE skills SET imbuing_xp = imbuing_xp - #{value} WHERE user_id = #{userId}")
    void decreaseImbuingXp(@Param("userId") int userId, @Param("value") long value);
}
