package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.AttributeAttemptEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.domain.AttributeAttemptEventEntryEvaluatorResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@ExplorationEvent
public class FarmingIsHardExplorationEventDefinition extends ExplorationEventDefinition {

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private AttributeAttemptEventEntryEvaluator attributeAttemptEventEntryEvaluator;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private Random random;

    @Override
    public int getId() {
        return 8;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("In the marketplace, a farmer approaches you and requests your assistance with some physical work. In his shed, he has multiple crates that must be loaded to a cart for shipment. However, the farmer suffers from back pains and cannot lift the crates by himself. The farmer offers you a reward after you agree to help him. The farmer is extremely grateful and gives you directions to his property. He hopes to see you bright and early.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("The following morning, you leave Sevgard and take a paved lane to a one-story home. As soon as you pass through a wooden gate, a hound dog races over and greets you with a friendly bark. The farmer notices you from his field and waves. The dog wags his tail and runs back to his master. You walk past several herb gardens and meet the farmer outside of his shed. He shakes your hand before opening the shed's rickety door. Inside, there are fifteen crates of varying sizes, and all of them contain crops.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("The farmer points to a cart several feet away and wants the crates loaded safely. You nod your head before getting straight to work. You lift the smallest crates first without effort and position them at the back of the cart. However, you begin to feel a strain in your muscles as the crates increase in size. You grit your teeth and push through the pain. You persist until there is only one crate left, and it is twice the size of the last.")
                        .build()
        );

        final AttributeAttemptEventEntryEvaluatorResult attemptResult = attributeAttemptEventEntryEvaluator.attributeAttempt(userEntity, GeneralAttribute.STRENGTH, 8);

        explorationResult.addEventEntryResults(attemptResult.getResult());

        int resultCoins = random.nextInt(6) + 3;

        if (attemptResult.isSuccessful()) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("You grasp the crate tightly and heave. To your relief, the crate lifts off the ground, and you raise it successfully to your chest. You walk slowly out of the shed despite the burning sensation in your shoulders and legs. You reach the cart and place it sharply on the back. You gasp for breath and lean against the cart, wiping sweat from your brow. The farmer appears and is very pleased with your work, and as promised, he gives you a pouch of copper coins.")
                            .build()
            );
        } else {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("You grasp the crate tightly and heave. To your frustration, the crate does not budge. You try one more time, and you feel as if you might pull a muscle. What is inside this crate? Rocks? The farmer appears behind you and is understanding. He gives you a pouch half full of copper coins. He requires the other half to hire someone stronger than you.")
                            .build()
            );

            resultCoins = resultCoins / 2;
        }

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        inventoryEntity.increaseMoneyAmount(MoneyType.MONEY, resultCoins);

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("**You gained " + resultCoins + " Bronze Coins.**")
                        .build()
        );

        return explorationResult;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.FARMFIELDS;
    }
}
