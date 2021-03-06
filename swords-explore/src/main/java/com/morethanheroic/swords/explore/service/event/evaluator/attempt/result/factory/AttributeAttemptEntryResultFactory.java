package com.morethanheroic.swords.explore.service.event.evaluator.attempt.result.factory;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.attempt.domain.AttributeAttemptCalculationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;

import java.util.List;

public interface AttributeAttemptEntryResultFactory {

    List<TextExplorationEventEntryResult> newResult(final Attribute attribute, final int valueToHit, final AttributeAttemptCalculationResult calculationResult);

    Attribute supportedAttribute();
}
