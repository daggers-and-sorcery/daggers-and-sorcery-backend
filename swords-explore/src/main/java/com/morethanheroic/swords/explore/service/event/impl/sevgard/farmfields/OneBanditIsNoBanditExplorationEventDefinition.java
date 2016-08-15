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
public class OneBanditIsNoBanditExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int EVENT_ID = 3;

    private static final int BANDIT_BRIGAND_MONSTER_ID = 10;

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
        opponent = combatEventEntryEvaluator.convertMonsterIdToDefinition(BANDIT_BRIGAND_MONSTER_ID);
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
                        .content("From Sevgard's mighty walls, you relax after a long day of adventuring to watch the sunset. The wind blows the wheat lazily and transforms the field into a sea of shimmering gold. You lean forward and feel at ease until something catches your eye. A straw hat meanders through the fields and disrupts the flowing wheat. It must belong to Pete the Farmer, the elderly man who toils in his fields day in and day out. You frown as you realize that Pete the Farmer has no farmhands and struggles to maintain his crops. You ponder this until the stars appear and the lanterns flicker to life. The firelight illuminates the straw hat shuffling haggardly to his home.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("In the morning, you pack a bag with sufficient supplies and leave Sevgard. You approach Farmfields and recall the path to Pete the Farmer's home. You plan on offering your services to the farmer, and hopefully, he would lower his pride and accept your offer. You daydream about fresh vegetables and meats but halt when you notice a dark figure ahead of you. A Bandit Brigand wearing all black leans against a wooden post and tosses a dagger up and down, catching it perfectly by the hilt. He notices you and snickers beneath a crimson bandana. He sprints toward you, giving you barely enough time to react.")
                        .build()
        );

        final CombatEventEntryEvaluatorResult combatResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent);

        explorationResult.addEventEntryResult(combatResult.getResult());

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
                            .content("As soon as the Bandit Brigand falls, you hear a rush of feathers. Massive, black crows descend upon the body, and you run away as their caws reach a fever pitch. You burst into Pete the Farmer's home and struggle to catch your breath. As you recover, you search for Pete the Farmer, but he is nowhere to be found. He must be out in the fields. You debate on waiting for him but decide against it. You write the farmer a concerned note and place it on the kitchen table. You linger in the doorway before leaving Pete the Farmer's house. You take a different path back to Sevgard.")
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
                    .newMessageEntry("ONE_BANDIT_IS_NO_BANDIT_EXPLORATION_EVENT_ENTRY_1")
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