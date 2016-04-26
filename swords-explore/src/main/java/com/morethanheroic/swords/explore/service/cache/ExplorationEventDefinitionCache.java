package com.morethanheroic.swords.explore.service.cache;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.domain.event.entry.impl.CombatExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.entry.impl.TextExplorationEventEntry;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    @Autowired
    private CombatCalculator combatCalculator;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    private List<ExplorationEventDefinition> explorationEventDefinitions = new ArrayList<>();

    @PostConstruct
    private void initialize() {
        //TODO: Move this to somewhere else!!!
        explorationEventDefinitions.add(
                ExplorationEventDefinition.builder()
                        .explorationEventEntries(Lists.newArrayList(
                                TextExplorationEventEntry.builder()
                                        .content("You awake from a refreshing nap and feel your stomach growl hungrily. As the last rays of sunlight recede through the nearby window, you slide out of bed and pack a satchel with supplies. You yearn to visit the inn outside the city, so you exit your dwelling and walk down the bustling streets of Sevgard until you arrive at the massive, oaken gate. A pair of guards waves you over the drawbridge, and you follow the beaten path to Farmfields. In the early evening, lanterns are flickering to life and illuminate the path to the quaint inn. This is the perfect opportunity to learn about the local goings-on, and your throat feels quite parched and your stomach quite empty.")
                                        .build(),
                                TextExplorationEventEntry.builder()
                                        .content("You enter the inn and sit at a table in the corner which allows you to see all of the patrons. Farmers and dusty adventurers surround the bar. Barmaids balance platters of steaming meats and tankards of sloshing ale. A quartet plays center stage whilst the tallest of the four sings a ditty about lost love. You hear a lute, a harp, an ocarina, and a soft drum, and you begin to relax to the tranquil music. Suddenly, the door slams open and silences the evening crowd.")
                                        .build(),
                                TextExplorationEventEntry.builder()
                                        .content("A band of green and yellow orcs surge inside and are searching for trouble. The boldest of the orcs knocks an elderly farmer to the ground while his cohorts snatch plates from patrons and scarf down their food. The orcs lick the plates clean with thick saliva dribbling from their ugly mugs, and they disperse to find more. Meanwhile, their leader stomps over to a barmaid and tries cornering her with a smug expression. The adventurers leave the bar and turn to fight the orcs. Before anyone can react, the two forces attack, and the closest adventurer tackles the orc leader to the ground. Objects, ale, food, and bodies go flying, and you duck your head as a roast chicken is tossed just over you.")
                                        .build(),
                                TextExplorationEventEntry.builder()
                                        .content("You stand up and draw the attention of a pale green Orc Brigand. He sneers at you with his crooked teeth and removes a sharp dagger from his leather belt. You return his glare and prepare yourself for a fight.")
                                        .build(),
                                //TODO: Use an Orc Brigand ere instead the Goblin one.
                                CombatExplorationEventEntry.builder()
                                        .monsterDefinition(monsterDefinitionCache.getMonsterDefinition(6))
                                        .combatCalculator(combatCalculator)
                                        .build(),
                                TextExplorationEventEntry.builder()
                                        .content("The Orc Brigand squeaks faintly, his body twitching, as he stumbles into a table and collapses face first. In the chaos, you turn tail and sprint straight for the exit. You reach the door undetected and escape into the night.")
                                        .build()
                                )
                        )
                        .build()
        );
    }

    @Override
    public ExplorationEventDefinition getDefinition(Integer key) {
        return explorationEventDefinitions.get(key);
    }
}
