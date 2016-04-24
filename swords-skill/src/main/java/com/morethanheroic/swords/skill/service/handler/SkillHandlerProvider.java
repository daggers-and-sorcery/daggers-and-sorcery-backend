package com.morethanheroic.swords.skill.service.handler;

import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Provide you a {@link SkillHandler} to work with.
 */
@Service
public class SkillHandlerProvider {

    @Autowired
    private List<SkillHandler> skillHandlers;

    private final Map<SkillType, SkillHandler> skillHandlerMap = new EnumMap<>(SkillType.class);

    @PostConstruct
    public void initialize() {
        for (SkillHandler skillHandler : skillHandlers) {
            skillHandlerMap.put(skillHandler.getSupportedSkillType(), skillHandler);
        }
    }

    public SkillHandler getSkillHandler(SkillType skillType) {
        Assert.isTrue(skillHandlerMap.containsKey(skillType), "No Skillhandler exists for SkillType: " + skillType + "!");

        return skillHandlerMap.get(skillType);
    }
}
