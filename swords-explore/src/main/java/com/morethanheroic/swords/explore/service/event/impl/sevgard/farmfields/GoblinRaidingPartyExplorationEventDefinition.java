package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoblinRaidingPartyExplorationEventDefinition extends ExplorationEventDefinition {

    private static final int GOBLIN_PIKEMAN_MONSTER_ID = 1;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private CombatCalculator combatCalculator;

    @Override
    public int getId() {
        return 7;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You respond to a flyer posted on Sevgard's notice board. A farmer just outside the city is having a goblin problem. They are destroying his crops and slaughtering his livestock. He requires immediate assistance dealing with them, and he promises a reward. You take the flyer with you and follow the directions to the farmer's property. ")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You knock on the farmer's door with the flyer in hand. You greet the farmer and explain that you want to help. The farmer is thrilled and beckons you inside his home. A worn map of Farmfields rests on the kitchen table, and he points out a set of hills behind his farm, insisting the goblins are hiding there. During the night, they appear and create the worst racket while tearing up his fields. He alerted the city guard to his plight, but they have yet to kill the goblins. That responsibility has now shifted to you.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You promise the goblins will be dead by nightfall and leave the farm promptly. The hills are a stone throw away, and you even see smoke rising from a cook fire. These goblins have become arrogant. It is the middle of the day! You trek up a slope and notice a goblin encampment with bones strewn everywhere. It seems like they have been gorging themselves. With an enraged cry, you charge down the hill and attack a Goblin Pikeman.")
                        .build()
        ).addEventEntryResult(
                CombatExplorationEventEntryResult.builder()
                        .combatMessages(combatCalculator.doFight(userEntity, monsterDefinitionCache.getMonsterDefinition(GOBLIN_PIKEMAN_MONSTER_ID)).getCombatMessages())
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("The Goblin Pikeman falls to the ground, and you whirl around. You caught the first Goblin Pikemen off guard, but the rest of them are ready.")
                        .build()
        ).addEventEntryResult(
                CombatExplorationEventEntryResult.builder()
                        .combatMessages(combatCalculator.doFight(userEntity, monsterDefinitionCache.getMonsterDefinition(GOBLIN_PIKEMAN_MONSTER_ID)).getCombatMessages())
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("Another Goblin Pikemen falls to the ground. You glare at the remainder. You are about to attack when you realize the goblins are running away. Cowards! You sprint after them, but they have a head start. The goblins disappear into the woods like rats, and it is impossible to follow them. You retreat to the farmer and describe the situation. You insist on staying the night in case the goblins return, and for your trouble, the farmer gives you a pouch of bronze coins.")
                        .build()
        );

        return explorationResult;
    }
}
