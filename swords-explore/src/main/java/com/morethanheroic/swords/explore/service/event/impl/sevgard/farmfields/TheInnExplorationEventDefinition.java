package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@ExplorationEvent
public class TheInnExplorationEventDefinition extends ExplorationEventDefinition {

    private static final int ORC_BRIGAND_MONSTER_ID = 6;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private CombatEventEntryEvaluator combatEventEntryEvaluator;

    private MonsterDefinition opponent;

    @PostConstruct
    private void initialize() {
        opponent = combatEventEntryEvaluator.convertMonsterIdToDefinition(ORC_BRIGAND_MONSTER_ID);
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You awake from a refreshing nap and feel your stomach growl hungrily. As the last rays of sunlight recede through the nearby window, you slide out of bed and pack a satchel with supplies. You yearn to visit the inn outside the city, so you exit your dwelling and walk down the bustling streets of Sevgard until you arrive at the massive, oaken gate. A pair of guards waves you over the drawbridge, and you follow the beaten path to Farmfields. In the early evening, lanterns are flickering to life and illuminate the path to the quaint inn. This is the perfect opportunity to learn about the local goings-on, and your throat feels quite parched and your stomach quite empty.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You enter the inn and sit at a table in the corner which allows you to see all of the patrons. Farmers and dusty adventurers surround the bar. Barmaids balance platters of steaming meats and tankards of sloshing ale. A quartet plays center stage whilst the tallest of the four sings a ditty about lost love. You hear a lute, a harp, an ocarina, and a soft drum, and you begin to relax to the tranquil music. Suddenly, the door slams open and silences the evening crowd.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("A band of green and yellow orcs surge inside and are searching for trouble. The boldest of the orcs knocks an elderly farmer to the ground while his cohorts snatch plates from patrons and scarf down their food. The orcs lick the plates clean with thick saliva dribbling from their ugly mugs, and they disperse to find more. Meanwhile, their leader stomps over to a barmaid and tries cornering her with a smug expression. The adventurers leave the bar and turn to fight the orcs. Before anyone can react, the two forces attack, and the closest adventurer tackles the orc leader to the ground. Objects, ale, food, and bodies go flying, and you duck your head as a roast chicken is tossed just over you.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You stand up and draw the attention of a pale green Orc Brigand. He sneers at you with his crooked teeth and removes a sharp dagger from his leather belt. You return his glare and prepare yourself for a fight.")
                        .build()
        );

        final CombatEventEntryEvaluatorResult combatResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent);

        explorationResult.addEventEntryResult(combatResult.getResult());

        if (!combatResult.getCombatResult().isPlayerVictory()) {
            return explorationResult;
        }

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("The Orc Brigand squeaks faintly, his body twitching, as he stumbles into a table and collapses face first. In the chaos, you turn tail and sprint straight for the exit. You reach the door undetected and escape into the night.")
                        .build()
        );

        return explorationResult;
    }
}
