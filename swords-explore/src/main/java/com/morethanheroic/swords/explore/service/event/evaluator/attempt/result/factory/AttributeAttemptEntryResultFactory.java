package com.morethanheroic.swords.explore.service.event.evaluator.attempt.result.factory;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.probe.domain.AttributeProbeCalculationResult;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;

import java.util.List;

public interface AttributeAttemptEntryResultFactory {

    List<ExplorationEventEntryResult> newResult(final Attribute attribute, final int valueToHit, final AttributeProbeCalculationResult calculationResult);

    Attribute supportedAttribute();
}
