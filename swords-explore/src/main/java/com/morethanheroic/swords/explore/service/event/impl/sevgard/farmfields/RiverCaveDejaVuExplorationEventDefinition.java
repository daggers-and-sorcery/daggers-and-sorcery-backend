package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@ExplorationEvent
public class RiverCaveDejaVuExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int EVENT_ID = 2;

    private static final int GOBLIN_PIKER_MONSTER_ID = 1;

    private static final int COMBAT_STAGE = 1;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private CombatEventEntryEvaluator combatEventEntryEvaluator;

    @Autowired
    private ExplorationResultBuilderFactory explorationResultBuilderFactory;

    private MonsterDefinition opponent;

    @PostConstruct
    private void initialize() {
        opponent = combatEventEntryEvaluator.convertMonsterIdToDefinition(GOBLIN_PIKER_MONSTER_ID);
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You wander aimlessly down the bank of the Hister River and feel the oppressiveness of the gloomy sky. You kick a pebble once, twice, three times before it plops into the river. You stop for a few moments and cross you arms, studying the water as it beckons to you. Your brows scrunch in confusion as the strangest sensation passes over you - deja vu. The rocks, the algae, the dragonflies, you have seen this all before, but you have never traveled by this way. You rub the back of your neck and ponder this matter further. Perhaps, it is a long lost dream or a bizarre twist of fate. Gut instinct navigates you upstream until you reach a craggy cave.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("The opening to the cave is quite small and has riverwater seeping from it. You approach the crack curiously and squint inside, but you see nothing but pure darkness. Your deja vu grows stronger, and you struggle to remember what lies beyond the rockface. Your mind is made up. You enter the cavern and feel the temperature around you immediately drop. You blink your eyes several times and wait for them to adjust. As soon as they do, you press onward and follow the tributary around a bend. The cavern widens with massive stalactites hanging over you, and you duck beneath one blocking your way.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("On the other side, you notice clusters of clay pots and urns with bronze coins scattered around them. However, you are not alone. A Goblin Piker sits crisscross in the middle of the room and seems to be meditating. His formidable pike rests across his lap with his green fingers clasped tightly around it. You debate on returning whence you came though an urn rests only a few feet away. As you pick it up, it slips straight from your grasp due to a thick coating of slime. The urn shatters and alerts the Goblin Piker. He snarls at you and raises his sharp pike.")
                        .build()
        );

        final CombatEventEntryEvaluatorResult combatResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent);

        explorationResult.addEventEntryResult(combatResult.getResult());

        userEntity.setActiveExploration(EVENT_ID, COMBAT_STAGE);

        return explorationResult;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.FARMFIELDS;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        if (stage == COMBAT_STAGE) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("The " + opponent.getName() + "'s body tumbles into the slow-moving river and washes downstream. You study the multitude of pots and urns and sense a foreboding presence. You no longer wish to meddle with this vile place. You leave the cavern and rejoice as the sun shines from behind the clouds. Your deja vu disappears.")
                            .build()
            );

            userEntity.resetActiveExploration();
        }

        return explorationResult;
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity)
                    .newMessageEntry("RIVER_CAVE_DEJA_VU_EXPLORATION_EVENT_ENTRY_1")
                    .continueCombatEntry()
                    .build();
        }

        return explorationResultFactory.newExplorationResult();
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }
}
