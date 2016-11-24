package com.morethanheroic.swords.ladder.service;

import com.morethanheroic.swords.ladder.domain.LadderEntry;
import com.morethanheroic.swords.ladder.repository.domain.LadderMapper;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.service.SkillLevelCalculator;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import com.morethanheroic.swords.skill.service.handler.SkillHandlerProvider;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LadderService {

    private static final int MINIMUM_PAGE_ID = 1;
    private static final int USER_PER_PAGE = 20;

    private final LadderMapper ladderMapper;
    private final UserEntityFactory userEntityFactory;
    private final SkillHandlerProvider skillHandlerProvider;
    private final SkillLevelCalculator skillLevelCalculator;

    public List<LadderEntry> getLadderData(final UserEntity userEntity, final SkillType skillType, final int page) {
        if (page < MINIMUM_PAGE_ID) {
            throw new IllegalArgumentException("Page should be minimum 1!");
        }

        final SkillHandler skillHandler = skillHandlerProvider.getSkillHandler(skillType);
        final List<SkillDatabaseEntity> skillDatabaseEntities = ladderMapper.getLadderData(getSkillColumnName(skillType), calculateStartingEntry(page), USER_PER_PAGE);

        return Collections.unmodifiableList(
                skillDatabaseEntities.stream()
                        .map(
                                skillDatabaseEntity -> LadderEntry.builder()
                                        .username(userEntityFactory.getEntity(skillDatabaseEntity.getUserId()).getUsername())
                                        .level(skillLevelCalculator.getLevelFromExperience(skillHandler.getExperience(skillDatabaseEntity)))
                                        .xp(skillHandler.getExperience(skillDatabaseEntity))
                                        .isMe(userEntity.getId() == skillDatabaseEntity.getUserId())
                                        .build()
                        )
                        .collect(Collectors.toList())
        );
    }

    public int pageCount() {
        return ladderMapper.getSkillCount() / USER_PER_PAGE;
    }

    private int calculateStartingEntry(final int page) {
        return page * USER_PER_PAGE - USER_PER_PAGE;
    }

    //TODO: We seriously need to do something better than this. I just have no better idea for it at the moment. :S
    private String getSkillColumnName(final SkillType skillType) {
        if (skillType == SkillType.STAFF) {
            return "staffs_xp";
        }
        if (skillType == SkillType.SPECTRE) {
            return "spectres_xp";
        }
        if (skillType == SkillType.WAND) {
            return "wands_xp";
        }

        return skillType.name().toLowerCase() + "_xp";
    }
}
