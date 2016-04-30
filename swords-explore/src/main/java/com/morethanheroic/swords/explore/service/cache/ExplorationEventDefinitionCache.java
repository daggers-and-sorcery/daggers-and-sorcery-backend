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
import java.util.HashMap;
import java.util.Map;

@Service
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    @Autowired
    private CombatCalculator combatCalculator;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    private Map<Integer, ExplorationEventDefinition> explorationEventDefinitions = new HashMap<>();

    @PostConstruct
    private void initialize() {
        //TODO: Move this to somewhere else!!!
        explorationEventDefinitions.put(1, ExplorationEventDefinition.builder()
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
        explorationEventDefinitions.put(2, ExplorationEventDefinition.builder()
                .explorationEventEntries(Lists.newArrayList(
                        TextExplorationEventEntry.builder()
                                .content("You wander aimlessly down the bank of the Hister River and feel the oppressiveness of the gloomy sky. You kick a pebble once, twice, three times before it plops into the river. You stop for a few moments and cross you arms, studying the water as it beckons to you. Your brows scrunch in confusion as the strangest sensation passes over you - deja vu. The rocks, the algae, the dragonflies, you have seen this all before, but you have never traveled by this way. You rub the back of your neck and ponder this matter further. Perhaps, it is a long lost dream or a bizarre twist of fate. Gut instinct navigates you upstream until you reach a craggy cave.")
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("The opening to the cave is quite small and has riverwater seeping from it. You approach the crack curiously and squint inside, but you see nothing but pure darkness. Your deja vu grows stronger, and you struggle to remember what lies beyond the rockface. Your mind is made up. You enter the cavern and feel the temperature around you immediately drop. You blink your eyes several times and wait for them to adjust. As soon as they do, you press onward and follow the tributary around a bend. The cavern widens with massive stalactites hanging over you, and you duck beneath one blocking your way.")
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("On the other side, you notice clusters of clay pots and urns with bronze coins scattered around them. However, you are not alone. A Goblin Piker sits crisscross in the middle of the room and seems to be meditating. His formidable pike rests across his lap with his green fingers clasped tightly around it. You debate on returning whence you came though an urn rests only a few feet away. As you pick it up, it slips straight from your grasp due to a thick coating of slime. The urn shatters and alerts the Goblin Piker. He snarls at you and raises his sharp pike.")
                                .build(),
                        CombatExplorationEventEntry.builder()
                                .monsterDefinition(monsterDefinitionCache.getMonsterDefinition(1))
                                .combatCalculator(combatCalculator)
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("The Goblin Piker's body tumbles into the slow-moving river and washes downstream. You study the multitude of pots and urns and sense a foreboding presence. You no longer wish to meddle with this vile place. You leave the cavern and rejoice as the sun shines from behind the clouds. Your deja vu disappears.")
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

    public int size() {
        return explorationEventDefinitions.size();
    }
}
