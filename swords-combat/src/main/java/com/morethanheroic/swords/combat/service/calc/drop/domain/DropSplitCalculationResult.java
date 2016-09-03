package com.morethanheroic.swords.combat.service.calc.drop.domain;

import com.morethanheroic.swords.combat.domain.Drop;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DropSplitCalculationResult {

    private final List<Drop> successfulResult;
    private final List<Drop> failedResult;
}
