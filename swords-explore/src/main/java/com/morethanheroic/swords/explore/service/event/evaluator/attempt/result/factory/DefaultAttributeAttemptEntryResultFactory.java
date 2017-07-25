package com.morethanheroic.swords.explore.service.event.evaluator.attempt.result.factory;

import com.google.common.collect.Lists;
import com.morethanheroic.localization.MessageResolver;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.attempt.domain.AttributeAttemptCalculationResult;
import com.morethanheroic.swords.attribute.service.cache.SkillAttributeDefinitionCache;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAttributeAttemptEntryResultFactory implements AttributeAttemptEntryResultFactory {

    private static final Attribute DEFAULT_RESULT_FACTORY_ID = null;

    private final MessageResolver messageResolver;
    private final SkillAttributeDefinitionCache skillAttributeDefinitionCache;

    @Override
    public List<TextExplorationEventEntryResult> newResult(final Attribute attribute, final int valueToHit, AttributeAttemptCalculationResult calculationResult) {
        return Lists.newArrayList(
                TextExplorationEventEntryResult.builder()
                        .content(messageResolver.resolveMessage(evaluateMessageIdFromResult(calculationResult.isSuccessful()), calculateAttributeName(attribute), valueToHit, calculationResult.getRolledValue()))
                        .build()
        );
    }

    @Override
    public Attribute supportedAttribute() {
        return DEFAULT_RESULT_FACTORY_ID;
    }

    private String evaluateMessageIdFromResult(final boolean result) {
        return result ? "ATTRIBUTE_PROBE_RESULT_SUCCESS" : "ATTRIBUTE_PROBE_RESULT_FAILURE";
    }

    private String calculateAttributeName(final Attribute attribute) {
        if (attribute instanceof SkillAttribute) {
            return skillAttributeDefinitionCache.getDefinition((SkillAttribute) attribute).getName();
        } else {
            return attribute.getName();
        }
    }
}
