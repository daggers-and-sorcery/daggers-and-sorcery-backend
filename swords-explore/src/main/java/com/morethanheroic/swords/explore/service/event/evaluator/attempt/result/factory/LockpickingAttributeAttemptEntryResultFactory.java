package com.morethanheroic.swords.explore.service.event.evaluator.attempt.result.factory;

import com.morethanheroic.localization.MessageResolver;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.cache.SkillAttributeDefinitionCache;
import com.morethanheroic.swords.attribute.service.attempt.domain.AttributeAttemptCalculationResult;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.skill.lockpicking.service.domain.LockpickingAttributeAttemptCalculatorExtensionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LockpickingAttributeAttemptEntryResultFactory implements AttributeAttemptEntryResultFactory {

    private final MessageResolver messageResolver;
    private final SkillAttributeDefinitionCache skillAttributeDefinitionCache;

    @Override
    public List<TextExplorationEventEntryResult> newResult(Attribute attribute, int valueToHit, AttributeAttemptCalculationResult calculationResult) {
        final LockpickingAttributeAttemptCalculatorExtensionResult lockpickingAttributeProbeCalculationResult = (LockpickingAttributeAttemptCalculatorExtensionResult) calculationResult.getExtensionResult();

        final List<TextExplorationEventEntryResult> results = new ArrayList<>();

        results.add(
                TextExplorationEventEntryResult.builder()
                        .content(messageResolver.resolveMessage("LOCKPICKING_ATTEMPT_TRYING_TO_LOCKPICK"))
                        .build()
        );

        if (!lockpickingAttributeProbeCalculationResult.isHadLockpick()) {
            results.add(
                    TextExplorationEventEntryResult.builder()
                            .content(messageResolver.resolveMessage("LOCKPICKING_ATTEMPT_NO_LOCKPICK"))
                            .build()
            );

            return results;
        }

        results.add(
                TextExplorationEventEntryResult.builder()
                        .content(messageResolver.resolveMessage(evaluateMessageIdFromResult(calculationResult.isSuccessful()), skillAttributeDefinitionCache.getDefinition((SkillAttribute) attribute).getName(), valueToHit, calculationResult.getRolledValue()))
                        .build()
        );

        if (lockpickingAttributeProbeCalculationResult.isLostLockpick()) {
            results.add(
                    TextExplorationEventEntryResult.builder()
                            .content(messageResolver.resolveMessage("LOCKPICKING_ATTEMPT_LOST_LOCKPICK"))
                            .build()
            );
        }

        results.add(
                TextExplorationEventEntryResult.builder()
                        .content(messageResolver.resolveMessage("LOCKPICKING_ATTEMPT_EXPERIENCE_GAINED", lockpickingAttributeProbeCalculationResult.getExperienceReward()))
                        .build()
        );

        return results;
    }

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }

    private String evaluateMessageIdFromResult(final boolean result) {
        return result ? "ATTRIBUTE_PROBE_RESULT_SUCCESS" : "ATTRIBUTE_PROBE_RESULT_FAILURE";
    }
}
