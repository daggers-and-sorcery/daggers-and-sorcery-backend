package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@ExplorationEvent
public class CaveAtTheRiverExplorationEventHandler extends MultiStageExplorationEventHandler {

    private static final int EVENT_ID = 5;

    private static final List<Integer> POSSIBLE_OPPONENTS = Lists.newArrayList(7, 8, 9);

    private static final int COMBAT_STAGE = 1;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private CombatEventEntryEvaluator combatEventEntryEvaluator;

    @Autowired
    private ExplorationResultBuilderFactory explorationResultBuilderFactory;

    @Autowired
    private CombatCalculator combatCalculator;

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    private List<MonsterDefinition> possibleOpponents;

    @PostConstruct
    private void initialize() {
        possibleOpponents = combatEventEntryEvaluator.convertMonsterIdToDefinition(POSSIBLE_OPPONENTS);
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));
        final MonsterDefinition opponent = combatEventEntryEvaluator.calculateOpponent(possibleOpponents);

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You explore the fields and walk through rows of tomatoes, carrots, lettuce and corn. You feel the urge to pick a few vegetables but restrain yourself. Shouldering your pack, you exit the fields before any of the farmers catch you trespassing. There is a slight chance that you trampled some of the crops a few yards back, and you do not wish to incite the farmers' anger. You hurry along until you come across the Hister River and follow it downstream. You feel calmer as you distance yourself from the fields.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("A school of silver fish enters the shallows and munches on the aquatic plants. You pause and study their movements until they swim away. You struggle to pursue them and frown when they disappear from sight. The path ends abruptly in large thorn thickets and prevents you from progressing further. You turn toward the river and cross to the other side by using the stones. You nearly slip on a slick rock but restore your balance after flailing your arms like a flightless bird. You reach the other side without your pride.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("A rock formation rests to your left, and you notice an opening to a large den. Upon closer inspection, there are piles of small animal bones scattered around the entrance along with pawprints. You stiffen as you hear a muffled growl from behind you. You turn slowly and see a " + opponent.getName() + " with a silver fish clenched in its jaws. The " + opponent.getName() + " drops the fish and charges you aggressively.")
                        .build()
        );

        final CombatEventEntryEvaluatorResult combatResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent, CombatType.EXPLORE);

        explorationResult.addEventEntryResult(combatResult.getResult());

        if(!combatResult.getResult().isPlayerDead()) {
            userEntity.setActiveExploration(EVENT_ID, COMBAT_STAGE);
        }

        return explorationResult;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));

        if (stage == COMBAT_STAGE) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("You glance to the den and sense that there might be more wild animals nearby. You retreat to the river and follow a dirt path back to the fields. You return to Sevgard safely.")
                            .build()
            );

            userEntity.resetActiveExploration();
        }

        return explorationResult;
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            if (combatCalculator.isCombatRunning(userEntity, CombatType.EXPLORE)) {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                        .newMessageEntry("CAVE_AT_THE_RIVER_EXPLORATION_EVENT_ENTRY_1", combatCalculator.getOpponentInRunningCombat(userEntity, CombatType.EXPLORE).getName())
                        .continueCombatEntry()
                        .build();
            } else {
                return explorationResultBuilderFactory
                        .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                        .continueCombatEntry()
                        .build();
            }
        }

        return explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }
}
