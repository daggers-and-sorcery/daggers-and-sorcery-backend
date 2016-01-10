package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.swords.cache.value.ValueCache;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.SkillValueCacheProvider;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Contains the data of the userEntity's skills in the game.
 */
public class SkillEntity {

    private static final int XP_UNTIL_LEVEL_TWO = 32;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private SkillValueCacheProvider skillValueCacheProvider;

    private final UserEntity userEntity;
    private ValueCache<SkillDatabaseEntity, SkillValueCacheProvider, UserEntity> skillValueCache;

    public SkillEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @PostConstruct
    public void initialize() {
        skillValueCache = new ValueCache<>(skillValueCacheProvider, userEntity);
    }

    //TODO: Do something with this!
    @SuppressWarnings({"checkstyle:cyclomaticcomplexity", "checkstyle:javancss", "checkstyle:ExecutableStatementCount"})
    public void addSkillXp(SkillType attribute, int value) {
        final SkillDatabaseEntity skillDatabaseEntity = skillValueCache.getEntity();

        switch (attribute) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                skillDatabaseEntity.setTwoHandedCrushingWeaponsXp(value);

                skillMapper.addTwoHandedCrushingWeaponsXp(userEntity.getId(), value);
                break;
            case ONE_HANDED_CRUSHING_WEAPONS:
                skillDatabaseEntity.setOneHandedCrushingWeaponsXp(value);

                skillMapper.addOneHandedCrushingWeaponsXp(userEntity.getId(), value);
                break;
            case TWO_HANDED_AXES:
                skillDatabaseEntity.setTwoHandedAxesXp(value);

                skillMapper.addTwoHandedAxesXp(userEntity.getId(), value);
                break;
            case ONE_HANDED_AXES:
                skillDatabaseEntity.setOneHandedAxesXp(value);

                skillMapper.addOneHandedAxesXp(userEntity.getId(), value);
                break;
            case THROWING_WEAPONS:
                skillDatabaseEntity.setThrowingWeaponsXp(value);

                skillMapper.addThrowingWeaponsXp(userEntity.getId(), value);
                break;
            case FISTFIGHT:
                skillDatabaseEntity.setFistfightXp(value);

                skillMapper.addFistfightXp(userEntity.getId(), value);
                break;
            case LONGSWORDS:
                skillDatabaseEntity.setLongswordsXp(value);

                skillMapper.addLongswordsXp(userEntity.getId(), value);
                break;
            case SHORTSWORDS:
                skillDatabaseEntity.setShortswordsXp(value);

                skillMapper.addShortswordsXp(userEntity.getId(), value);
                break;
            case POLEARMS:
                skillDatabaseEntity.setPolearmsXp(value);

                skillMapper.addPolearmsXp(userEntity.getId(), value);
                break;
            case DAGGERS:
                skillDatabaseEntity.setDaggersXp(value);

                skillMapper.addDaggersXp(userEntity.getId(), value);
                break;
            case LONGBOWS:
                skillDatabaseEntity.setLongswordsXp(value);

                skillMapper.addLongbowsXp(userEntity.getId(), value);
                break;
            case SHORTBOWS:
                skillDatabaseEntity.setShortswordsXp(value);

                skillMapper.addShortbowsXp(userEntity.getId(), value);
                break;
            case CROSSBOWS:
                skillDatabaseEntity.setCrossbowsXp(value);

                skillMapper.addCrossbowsXp(userEntity.getId(), value);
                break;
            case LIGHT_ARMOR:
                skillDatabaseEntity.setLightArmorXp(value);

                skillMapper.addLightArmorXp(userEntity.getId(), value);
                break;
            case HEAVY_ARMOR:
                skillDatabaseEntity.setHeavyArmorXp(value);

                skillMapper.addHeavyArmorXp(userEntity.getId(), value);
                break;
            case ROBE_ARMOR:
                skillDatabaseEntity.setRobeArmorXp(value);

                skillMapper.addRobeArmorXp(userEntity.getId(), value);
                break;
            case ARMORLESS_DEFENSE:
                skillDatabaseEntity.setArmorlessDefenseXp(value);

                skillMapper.addArmorlessDefenseXp(userEntity.getId(), value);
                break;
            case SHIELD_DEFENSE:
                skillDatabaseEntity.setShieldDefenseXp(value);

                skillMapper.addShieldDefenseXp(userEntity.getId(), value);
                break;
            case STAFF:
                skillDatabaseEntity.setStaffsXp(value);

                skillMapper.addStaffsXp(userEntity.getId(), value);
                break;
            case WAND:
                skillDatabaseEntity.setWandsXp(value);

                skillMapper.addWandsXp(userEntity.getId(), value);
                break;
            case SPECTRE:
                skillDatabaseEntity.setSpectresXp(value);

                skillMapper.addSpectresXp(userEntity.getId(), value);
                break;
            case SCAVENGING:
                skillDatabaseEntity.setScavengingXp(value);

                skillMapper.addScavengingXp(userEntity.getId(), value);
                break;
            case COOKING:
                skillDatabaseEntity.setCookingXp(value);

                skillMapper.addCookingXp(userEntity.getId(), value);
                break;
            default:
                throw new IllegalArgumentException("Unknown attribute: " + attribute);
        }
    }

    @SuppressWarnings({"checkstyle:cyclomaticcomplexity", "checkstyle:javancss"})
    public int getSkillXp(SkillType attribute) {
        final SkillDatabaseEntity skills = skillValueCache.getEntity();

        switch (attribute) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                return skills.getTwoHandedCrushingWeaponsXp();
            case ONE_HANDED_CRUSHING_WEAPONS:
                return skills.getOneHandedCrushingWeaponsXp();
            case TWO_HANDED_AXES:
                return skills.getTwoHandedAxesXp();
            case ONE_HANDED_AXES:
                return skills.getOneHandedAxesXp();
            case THROWING_WEAPONS:
                return skills.getThrowingWeaponsXp();
            case FISTFIGHT:
                return skills.getFistfightXp();
            case LONGSWORDS:
                return skills.getLongswordsXp();
            case SHORTSWORDS:
                return skills.getShortswordsXp();
            case POLEARMS:
                return skills.getPolearmsXp();
            case DAGGERS:
                return skills.getDaggersXp();
            case LONGBOWS:
                return skills.getLongswordsXp();
            case SHORTBOWS:
                return skills.getShowrtbowsXp();
            case CROSSBOWS:
                return skills.getCrossbowsXp();
            case LIGHT_ARMOR:
                return skills.getLightArmorXp();
            case HEAVY_ARMOR:
                return skills.getHeavyArmorXp();
            case ROBE_ARMOR:
                return skills.getRobeArmorXp();
            case ARMORLESS_DEFENSE:
                return skills.getArmorlessDefenseXp();
            case SHIELD_DEFENSE:
                return skills.getShieldDefenseXp();
            case STAFF:
                return skills.getStaffsXp();
            case WAND:
                return skills.getWandsXp();
            case SPECTRE:
                return skills.getSpectresXp();
            case SCAVENGING:
                return skills.getSpectresXp();
            case COOKING:
                return skills.getCookingXp();
            default:
                throw new IllegalArgumentException("Unknown attribute: " + attribute);
        }
    }

    public int getSkillLevel(SkillType attribute) {
        return getSkillLevelFromXp(getSkillXp(attribute));
    }

    public int getSkillXpToNextLevel(SkillType attribute) {
        return getSkillXpFromLevel(getSkillLevel(attribute) + 1);
    }

    public int getSkillXpBetweenNextLevel(SkillType attribute) {
        return getSkillXpFromLevel(getSkillLevel(attribute) + 1) - getSkillXpFromLevel(getSkillLevel(attribute));
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public int getSkillXpFromLevel(int level) {
        if (level < 1) {
            return 0;
        }

        return (int) Math.ceil((Math.pow((double) level, (double) 2) * (((double) level * (double) level) / (double) 4) + (double) 60) / (double) 2);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public int getSkillLevelFromXp(long xp) {
        if (xp < XP_UNTIL_LEVEL_TWO) {
            return 1;
        }

        return (int) Math.floor(Math.pow((double) 8 * (double) xp - (double) 240, 0.25));
    }
}
