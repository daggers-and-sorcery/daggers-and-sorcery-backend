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
public class TheBridgeExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int EVENT_ID = 4;

    private static final int GOBLIN_GUARD_MONSTER_ID = 2;

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
        opponent = combatEventEntryEvaluator.convertMonsterIdToDefinition(GOBLIN_GUARD_MONSTER_ID);
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
                        .content("You follow a cobblestone path to a wooden bridge and cross to the center of it. You lean against the guardrail and spot a mill upstream. A gigantic waterwheel directs the Hister River in your direction, and you wonder if it is a part of the Farmfields irrigation system. The river's flow is tranquil beneath the bridge without any rapids or rock clusters, so you close your eyes to listen. A breeze blows by you and carries a sweet scent upon it. Where is that coming from? A cloud of smoke forms beneath the bridge and rises through the cracks in the wood. You step back as the smoke grows darker and floats away.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("Instantly, you are curious and cross off of the bridge. You stop at the edge of a muddy slope and kneel down. As soon as your boots touch the mud, you slide all the way to the bottom. You catch yourself and release a relieved sigh. An outcopping of grey rock blocks the way, so you enter the river to travel around. You walk until you are knee high in the water, and that's when you spot the Goblin Guard.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("The Goblin Guard crouches next to a cook fire, and he turns a crudely constructed spit. A skinned rat spins slowly with its long tail dipping into the flames. The rat tail burns off, and with gnashing teeth, the Goblin Guard snatches it from the embers and shoves it into his mouth. He struggles to eat the rubbery tail but seems to be enjoying the taste. The Goblin Guard turns to prep another rat when he notices you standing in the river. He shrieks furiously and attacks you.")
                        .build()
        );

        final CombatEventEntryEvaluatorResult combatResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent);

        explorationResult .addEventEntryResult(combatResult.getResult());

        if(!combatResult.getResult().isPlayerDead()) {
            userEntity.setActiveExploration(EVENT_ID, COMBAT_STAGE);
        }

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
                            .content("While the Goblin Guard's body washes away, you are drawn to his cookfire. The rat is charred on one side, but the other side still appears fine. You remove the rat from the spit and eat the savory parts while you visit the nearby farms. Once you finish, you throw the charred bits into a pig trough. Not too bad. Rats taste like chicken.")
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
                    .newMessageEntry("THE_BRIDGE_EXPLORATION_EVENT_ENTRY_1")
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
