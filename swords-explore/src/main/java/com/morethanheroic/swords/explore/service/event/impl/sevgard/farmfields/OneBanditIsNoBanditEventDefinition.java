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
public class OneBanditIsNoBanditEventDefinition extends ExplorationEventDefinition {

    private static final int BANDIT_BRIGAND_MONSTER_ID = 10;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private CombatCalculator combatCalculator;

    @Override
    public int getId() {
        return 3;
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
        ).addEventEntryResult(
                CombatExplorationEventEntryResult.builder()
                        .combatMessages(combatCalculator.doFight(userEntity, monsterDefinitionCache.getMonsterDefinition(BANDIT_BRIGAND_MONSTER_ID)).getCombatMessages())
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("As soon as the Bandit Brigand falls, you hear a rush of feathers. Massive, black crows descend upon the body, and you run away as their caws reach a fever pitch. You burst into Pete the Farmer's home and struggle to catch your breath. As you recover, you search for Pete the Farmer, but he is nowhere to be found. He must be out in the fields. You debate on waiting for him but decide against it. You write the farmer a concerned note and place it on the kitchen table. You linger in the doorway before leaving Pete the Farmer's house. You take a different path back to Sevgard.")
                        .build()
        );

        return explorationResult;
    }
}