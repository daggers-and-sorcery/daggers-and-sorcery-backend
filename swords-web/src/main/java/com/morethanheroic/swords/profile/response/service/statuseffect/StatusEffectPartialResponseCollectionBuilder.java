package com.morethanheroic.swords.profile.response.service.statuseffect;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration.StatusEffectModifierResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration.StatusEffectResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.response.StatusEffectPartialResponse;
import com.morethanheroic.swords.statuseffect.service.StatusEffectEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@link PartialResponseBuilder} to build the status effect part of the character page.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<StatusEffectResponseBuilderConfiguration> {

    private final StatusEffectEntityFactory statusEffectEntityFactory;
    private final StatusEffectModifierPartialResponseBuilder statusEffectModifierPartialResponseBuilder;

    @Override
    public List<PartialResponse> build(final StatusEffectResponseBuilderConfiguration statusEffectResponseBuilderConfiguration) {
        return statusEffectEntityFactory.getEntity(statusEffectResponseBuilderConfiguration.getUserEntity()).stream()
                .map(statusEffectEntity -> StatusEffectPartialResponse.builder()
                        .id(statusEffectEntity.getStatusEffect().getId())
                        .name(statusEffectEntity.getStatusEffect().getName())
                        .description(statusEffectEntity.getStatusEffect().getDescription())
                        .expirationTime(statusEffectEntity.getExpirationTime())
                        .modifiers(
                                statusEffectEntity.getStatusEffect().getModifiers().stream()
                                        .map(statusEffectModifierDefinition -> statusEffectModifierPartialResponseBuilder.build(
                                                StatusEffectModifierResponseBuilderConfiguration.builder()
                                                        .userEntity(statusEffectResponseBuilderConfiguration.getUserEntity())
                                                        .statusEffectModifierDefinition(statusEffectModifierDefinition)
                                                        .build()
                                                )
                                        )
                                        .flatMap(partialResponses -> partialResponses.stream())
                                        .collect(Collectors.toList())
                        )
                        .build()
                )
                .collect(Collectors.toList());
    }
}
