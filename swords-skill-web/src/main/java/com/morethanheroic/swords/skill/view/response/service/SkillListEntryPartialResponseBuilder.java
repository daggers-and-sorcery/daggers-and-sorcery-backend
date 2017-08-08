package com.morethanheroic.swords.skill.view.response.service;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.view.response.domain.SkillListEntryPartialResponse;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillListResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillListEntryPartialResponseBuilder implements PartialResponseCollectionBuilder<SkillListResponseBuilderConfiguration> {

    @Override
    public List<SkillListEntryPartialResponse> build(final SkillListResponseBuilderConfiguration skillListResponseBuilderConfiguration) {
        final List<SkillListEntryPartialResponse> result = new ArrayList<>();

        for (SkillType skillType : SkillType.values()) {
            result.add(
                    SkillListEntryPartialResponse.builder()
                            .id(skillType.name())
                            .name(skillType.getName())
                            .build()
            );
        }

        return result;
    }
}
