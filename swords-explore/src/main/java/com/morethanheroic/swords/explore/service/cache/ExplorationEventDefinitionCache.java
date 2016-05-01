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
        explorationEventDefinitions.put(3, ExplorationEventDefinition.builder()
                .explorationEventEntries(Lists.newArrayList(
                        TextExplorationEventEntry.builder()
                                .content("From Sevgard's mighty walls, you relax after a long day of adventuring to watch the sunset. The wind blows the wheat lazily and transforms the field into a sea of shimmering gold. You lean forward and feel at ease until something catches your eye. A straw hat meanders through the fields and disrupts the flowing wheat. It must belong to Pete the Farmer, the elderly man who toils in his fields day in and day out. You frown as you realize that Pete the Farmer has no farmhands and struggles to maintain his crops. You ponder this until the stars appear and the lanterns flicker to life. The firelight illuminates the straw hat shuffling haggardly to his home.")
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("In the morning, you pack a bag with sufficient supplies and leave Sevgard. You approach Farmfields and recall the path to Pete the Farmer's home. You plan on offering your services to the farmer, and hopefully, he would lower his pride and accept your offer. You daydream about fresh vegetables and meats but halt when you notice a dark figure ahead of you. A Bandit Brigand wearing all black leans against a wooden post and tosses a dagger up and down, catching it perfectly by the hilt. He notices you and snickers beneath a crimson bandana. He sprints toward you, giving you barely enough time to react.")
                                .build(),
                        CombatExplorationEventEntry.builder()
                                .monsterDefinition(monsterDefinitionCache.getMonsterDefinition(6))
                                .combatCalculator(combatCalculator)
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("As soon as the Bandit Brigand falls, you hear a rush of feathers. Massive, black crows descend upon the body, and you run away as their caws reach a fever pitch. You burst into Pete the Farmer's home and struggle to catch your breath. As you recover, you search for Pete the Farmer, but he is nowhere to be found. He must be out in the fields. You debate on waiting for him but decide against it. You write the farmer a concerned note and place it on the kitchen table. You linger in the doorway before leaving Pete the Farmer's house. You take a different path back to Sevgard. ")
                                .build()
                        )
                )
                .build()
        );
        explorationEventDefinitions.put(4, ExplorationEventDefinition.builder()
                .explorationEventEntries(Lists.newArrayList(
                        TextExplorationEventEntry.builder()
                                .content("You follow a cobblestone path to a wooden bridge and cross to the center of it. You lean against the guardrail and spot a mill upstream. A gigantic waterwheel directs the Hister River in your direction, and you wonder if it is a part of the Farmfields irrigation system. The river's flow is tranquil beneath the bridge without any rapids or rock clusters, so you close your eyes to listen. A breeze blows by you and carries a sweet scent upon it. Where is that coming from? A cloud of smoke forms beneath the bridge and rises through the cracks in the wood. You step back as the smoke grows darker and floats away.")
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("Instantly, you are curious and cross off of the bridge. You stop at the edge of a muddy slope and kneel down. As soon as your boots touch the mud, you slide all the way to the bottom. You catch yourself and release a relieved sigh. An outcopping of grey rock blocks the way, so you enter the river to travel around. You walk until you are knee high in the water, and that's when you spot the Goblin Guard.")
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("The Goblin Guard crouches next to a cook fire, and he turns a crudely constructed spit. A skinned rat spins slowly with its long tail dipping into the flames. The rat tail burns off, and with gnashing teeth, the Goblin Guard snatches it from the embers and shoves it into his mouth. He struggles to eat the rubbery tail but seems to be enjoying the taste. The Goblin Guard turns to prep another rat when he notices you standing in the river. He shrieks furiously and attacks you.")
                                .build(),
                        CombatExplorationEventEntry.builder()
                                .monsterDefinition(monsterDefinitionCache.getMonsterDefinition(2))
                                .combatCalculator(combatCalculator)
                                .build(),
                        TextExplorationEventEntry.builder()
                                .content("While the Goblin Guard's body washes away, you are drawn to his cookfire. The rat is charred on one side, but the other side still appears fine. You remove the rat from the spit and eat the savory parts while you visit the nearby farms. Once you finish, you throw the charred bits into a pig trough. Not too bad. Rats taste like chicken.")
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
