package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.swords.cache.value.ValueCache;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.skill.service.SkillValueCacheProvider;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Contains the data of the user's skills like level and experience. Don't create it manually, use
 * {@link com.morethanheroic.swords.skill.service.factory.SkillEntityFactory} instead.
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
            case TWO_HANDED_CRUSHING_WEAPONS: //x
                //TODO: BUG: SET is not good!!!! We must add the new value or set the good value!
                skillDatabaseEntity.setTwoHandedCrushingWeaponsXp(value);

                skillMapper.increaseTwoHandedCrushingWeaponsXp(userEntity.getId(), value);
                break;
            case ONE_HANDED_CRUSHING_WEAPONS: //x
                skillDatabaseEntity.setOneHandedCrushingWeaponsXp(value);

                skillMapper.increaseOneHandedCrushingWeaponsXp(userEntity.getId(), value);
                break;
            case TWO_HANDED_AXES: //x
                skillDatabaseEntity.setTwoHandedAxesXp(value);

                skillMapper.increaseTwoHandedAxesXp(userEntity.getId(), value);
                break;
            case ONE_HANDED_AXES: //x
                skillDatabaseEntity.setOneHandedAxesXp(value);

                skillMapper.increaseOneHandedAxesXp(userEntity.getId(), value);
                break;
            case THROWING_WEAPONS: //x
                skillDatabaseEntity.setThrowingWeaponsXp(value);

                skillMapper.increaseThrowingWeaponsXp(userEntity.getId(), value);
                break;
            case FISTFIGHT: //x
                skillDatabaseEntity.setFistfightXp(value);

                skillMapper.increaseFistfightXp(userEntity.getId(), value);
                break;
            case LONGSWORDS: //x
                skillDatabaseEntity.setLongswordsXp(value);

                skillMapper.increaseLongswordsXp(userEntity.getId(), value);
                break;
            case SHORTSWORDS: //x
                skillDatabaseEntity.setShortswordsXp(value);

                skillMapper.increaseShortswordsXp(userEntity.getId(), value);
                break;
            case POLEARMS: //x
                skillDatabaseEntity.setPolearmsXp(value);

                skillMapper.increasePolearmsXp(userEntity.getId(), value);
                break;
            case DAGGERS: //x
                skillDatabaseEntity.setDaggersXp(value);

                skillMapper.increaseDaggersXp(userEntity.getId(), value);
                break;
            case LONGBOWS: //x
                skillDatabaseEntity.setLongswordsXp(value);

                skillMapper.increaseLongbowsXp(userEntity.getId(), value);
                break;
            case SHORTBOWS: //x
                skillDatabaseEntity.setShortswordsXp(value);

                skillMapper.increaseShortbowsXp(userEntity.getId(), value);
                break;
            case CROSSBOWS: //x
                skillDatabaseEntity.setCrossbowsXp(value);

                skillMapper.increaseCrossbowsXp(userEntity.getId(), value);
                break;
            case LIGHT_ARMOR: //x
                skillDatabaseEntity.setLightArmorXp(value);

                skillMapper.increaseLightArmorXp(userEntity.getId(), value);
                break;
            case HEAVY_ARMOR: //x
                skillDatabaseEntity.setHeavyArmorXp(value);

                skillMapper.increaseHeavyArmorXp(userEntity.getId(), value);
                break;
            case ROBE_ARMOR: //x
                skillDatabaseEntity.setRobeArmorXp(value);

                skillMapper.increaseRobeArmorXp(userEntity.getId(), value);
                break;
            case ARMORLESS_DEFENSE: //x
                skillDatabaseEntity.setArmorlessDefenseXp(value);

                skillMapper.increaseArmorlessDefenseXp(userEntity.getId(), value);
                break;
            case SHIELD_DEFENSE: //x
                skillDatabaseEntity.setShieldDefenseXp(value);

                skillMapper.increaseShieldDefenseXp(userEntity.getId(), value);
                break;
            case STAFF: //x
                skillDatabaseEntity.setStaffsXp(value);

                skillMapper.increaseStaffsXp(userEntity.getId(), value);
                break;
            case WAND: //x
                skillDatabaseEntity.setWandsXp(value);

                skillMapper.increaseWandsXp(userEntity.getId(), value);
                break;
            case SPECTRE: //x
                skillDatabaseEntity.setSpectresXp(value);

                skillMapper.increaseSpectresXp(userEntity.getId(), value);
                break;
            case SCAVENGING: //x
                skillDatabaseEntity.setScavengingXp(value);

                skillMapper.increaseScavengingXp(userEntity.getId(), value);
                break;
            case COOKING: //x
                skillDatabaseEntity.setCookingXp(value);

                skillMapper.increaseCookingXp(userEntity.getId(), value);
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
                return skills.getShortbowsXp();
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
