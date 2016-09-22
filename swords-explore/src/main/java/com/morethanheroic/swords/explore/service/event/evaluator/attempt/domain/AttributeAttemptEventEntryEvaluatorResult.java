package com.morethanheroic.swords.explore.service.event.evaluator.attempt.domain;

import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AttributeAttemptEventEntryEvaluatorResult {

    private boolean successful;
    private List<TextExplorationEventEntryResult> result;
}
