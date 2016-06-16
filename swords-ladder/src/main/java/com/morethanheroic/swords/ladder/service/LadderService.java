package com.morethanheroic.swords.ladder.service;

import com.morethanheroic.swords.ladder.domain.LadderEntry;
import com.morethanheroic.swords.ladder.repository.domain.LadderMapper;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.service.SkillLevelCalculator;
import com.morethanheroic.swords.skill.service.handler.SkillHandler;
import com.morethanheroic.swords.skill.service.handler.SkillHandlerProvider;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LadderService {

    private static final int USER_PER_PAGE = 20;

    @Autowired
    private LadderMapper ladderMapper;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private SkillHandlerProvider skillHandlerProvider;

    @Autowired
    private SkillLevelCalculator skillLevelCalculator;

    public List<LadderEntry> getLadderData(UserEntity userEntity, SkillType skillType, int page) {
        if (page < 1) {
            throw new IllegalArgumentException("Page should be minimum 1!");
        }

        final SkillHandler skillHandler = skillHandlerProvider.getSkillHandler(skillType);
        final List<SkillDatabaseEntity> skillDatabaseEntities = ladderMapper.getLadderData(getSkillColumnName(skillType), page * USER_PER_PAGE - USER_PER_PAGE, page * USER_PER_PAGE);

        final List<LadderEntry> result = new ArrayList<>();
        for (SkillDatabaseEntity skillDatabaseEntity : skillDatabaseEntities) {
            result.add(LadderEntry.builder()
                    .username(userFacade.getUser(skillDatabaseEntity.getUserId()).getUsername())
                    .level(skillLevelCalculator.getLevelFromExperience(skillHandler.getExperience(skillDatabaseEntity)))
                    .xp(skillHandler.getExperience(skillDatabaseEntity))
                    .isMe(userEntity.getId() == skillDatabaseEntity.getUserId())
                    .build()
            );
        }

        return result;
    }

    public int pageCount() {
        return ladderMapper.getSkillCount() / USER_PER_PAGE;
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
