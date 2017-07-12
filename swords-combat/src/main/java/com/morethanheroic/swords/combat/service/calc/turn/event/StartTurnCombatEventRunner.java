package com.morethanheroic.swords.combat.service.calc.turn.event;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.event.turn.StartTurnCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StartTurnCombatEventRunner {

    private final List<StartTurnCombatEventHandler> startTurnCombatEventHandlers;

    public List<CombatStep> runEvents(final StartTurnCombatEventContext startTurnCombatEventContext) {
        return startTurnCombatEventHandlers.stream()
                .flatMap(startTurnCombatEventHandler -> startTurnCombatEventHandler.handleEvent(startTurnCombatEventContext).stream())
                .collect(Collectors.toList());
    }
}
