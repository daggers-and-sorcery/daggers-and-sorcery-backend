package com.morethanheroic.swords.skill.view.response.service;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.attribute.service.cache.SkillAttributeDefinitionCache;
import com.morethanheroic.swords.skill.view.response.domain.SkillListEntryPartialResponse;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillListResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@link PartialResponseCollectionBuilder} to build responses that contain the data of the skills available in the game.
 */
@Service
@RequiredArgsConstructor
public class SkillListEntryPartialResponseBuilder implements PartialResponseCollectionBuilder<SkillListResponseBuilderConfiguration> {

    private final SkillAttributeDefinitionCache skillAttributeDefinitionCache;

    @Override
    public List<SkillListEntryPartialResponse> build(final SkillListResponseBuilderConfiguration skillListResponseBuilderConfiguration) {
        return skillAttributeDefinitionCache.getDefinitions().stream()
                .map(attributeDefinition ->
                        SkillListEntryPartialResponse.builder()
                                .id(attributeDefinition.getName())
                                .name(attributeDefinition.getName())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
