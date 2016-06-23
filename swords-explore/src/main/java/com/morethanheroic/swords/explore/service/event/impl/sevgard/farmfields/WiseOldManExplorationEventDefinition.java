package com.morethanheroic.swords.explore.service.event.impl.sevgard.farmfields;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.service.CombatUtil;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.OptionExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.option.EventOption;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventDefinition;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.item.domain.WeaponSuperType;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WiseOldManExplorationEventDefinition extends MultiStageExplorationEventDefinition {

    private static final int WELCOME_STAGE = 1;
    private static final int GIVE_COIN_STAGE = 2;
    private static final int DONT_GIVE_COIN_STAGE = 3;

    private static final int MONEY_TO_PAY = 2;

    private static final int EXPERIENCE_REWARD = 60;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Autowired
    private CombatUtil combatUtil;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Override
    public int getId() {
        return 6;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You are traveling to Farmfields when you encounter an old man sitting on the side of the road. His long, dusty beard flows over his sack-like garb. His gnarled fingers grasp an oaken staff, and you notice that he is barefoot. He appears to be a simple beggar, yet you sense an ominous power emanating from him.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("\"Halt!\"")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("You stop immediately and glance to the old man.")
                        .build()
        ).addEventEntryResult(
                TextExplorationEventEntryResult.builder()
                        .content("\"May you spare coin for a hapless, old soul?\"")
                        .build()
        ).addEventEntryResult(
                OptionExplorationEventEntryResult.builder()
                        .options(Lists.newArrayList(
                                EventOption.builder()
                                        .text("Give the man 2 bronze coins.")
                                        .optionId(GIVE_COIN_STAGE)
                                        .build(),
                                EventOption.builder()
                                        .text("Don't give him anything.")
                                        .optionId(DONT_GIVE_COIN_STAGE)
                                        .build()
                        ))
                        .build()
        );

        userEntity.setActiveExploration(6, WELCOME_STAGE);

        return explorationResult;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        if (stage == GIVE_COIN_STAGE) {
            final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);
            if (inventoryEntity.getMoneyAmount(MoneyType.MONEY) < MONEY_TO_PAY) {
                explorationResult.addEventEntryResult(
                        TextExplorationEventEntryResult.builder()
                                .content("You don't have 2 bronze to pay. Maybe next time.")
                                .build()
                ).addEventEntryResult(
                        TextExplorationEventEntryResult.builder()
                                .content("\"Be safe. These roads are treacherous even during the day,\" the man warns.")
                                .build()
                ).addEventEntryResult(
                        TextExplorationEventEntryResult.builder()
                                .content("\"I will.\" You turn and carry on your way.")
                                .build()
                );

                userEntity.resetActiveExploration();

                return explorationResult;
            }

            inventoryEntity.decreaseMoneyAmount(MoneyType.MONEY, MONEY_TO_PAY);

            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("***Two bronze coins is removed from your pouch.***")
                            .build()
            ).addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("\"Of course.\" You fumble for your belt and remove a coin pouch. You cross over and kneel before the man. You count out a few bronze coins and drop them kindly into his outstretched hand. The man smiles and reveals that he is missing a few teeth. Without warning, the man becomes a grey blur and snatches your weapon.")
                            .build()
            );

            final Optional<WeaponSuperType> weaponSuperType = combatUtil.getUserWeaponSuperType(userEntity);
            if (!weaponSuperType.isPresent()) {
                explorationResult.addEventEntryResult(
                        TextExplorationEventEntryResult.builder()
                                .content("However, you have no weapon to snatch. The old man seems puzzled for a moment before receiving an idea. He steps back, says an incantation, and a ball of fire appears in his palm. Your eyes widen as the fire flickers yet never burns the man. The man presses his palms together and snuffs out the flame. Only smoke remains. You are speechless for a few moments. \"That...that was amazing!\" you exclaim.")
                                .build()
                );
            } else {
                switch (weaponSuperType.get()) {
                    case MEELE:
                        explorationResult.addEventEntryResult(
                                TextExplorationEventEntryResult.builder()
                                        .content("He wrenches your weapon from your grasp and shouts a series of strange spells. Blue fireballs appear above you, and the old man stabs at them wildly. The fireballs disintegrate as soon as they're touched. The old man returns your weapon, chuckles to himself, and sits criss-cross. You are speechless for a few moments. \"That...that was amazing!\" you exclaim.")
                                        .build()
                        );
                        break;
                    case MAGIC:
                        explorationResult.addEventEntryResult(
                                TextExplorationEventEntryResult.builder()
                                        .content("He wrenches your weapon from your grasp and mutters an incantation. The tip of your wand glows bright red, and a fireball bursts outward and collides with a nearby tree. The magical fire burns for a few seconds before putting itself out. The old man returns your wand, chuckles to himself, and sits criss-cross. You are speechless for a few moments. \"That...that was amazing!\" you exclaim.")
                                        .build()
                        );
                        break;
                    case RANGED:
                        explorationResult.addEventEntryResult(
                                TextExplorationEventEntryResult.builder()
                                        .content("He wrenches your weapon from your grasp and summons an arrow in a puff of smoke. He nocks the arrow and pulls back on the bowstring. With startling precision, he shoots an acorn from a nearby tree and returns your weapon. The old man chuckles to himself before sitting criss-cross again. You are speechless for a few moments. \"That...that was amazing!\" you exclaim.")
                                        .build()
                        );
                        break;
                }
            }
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("\"It was nothing. Best not tarry,\" the old man insists.")
                            .build()
            ).addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("You yearn to see more magic, but you honor the old man's wishes. You bid him goodbye and carry on your way.")
                            .build()
            );

            final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);
            final SkillType weaponSkillType = combatUtil.getUserWeaponSkillType(userEntity);
            if (weaponSkillType != null) {
                skillEntity.increaseExperience(weaponSkillType, EXPERIENCE_REWARD);

                explorationResult.addEventEntryResult(
                        TextExplorationEventEntryResult.builder()
                                .content("***You gained " + EXPERIENCE_REWARD + " experience in the " + weaponSkillType.getName() + " skill.***")
                                .build()
                );
            } else {
                skillEntity.increaseExperience(SkillType.FISTFIGHT, EXPERIENCE_REWARD);

                explorationResult.addEventEntryResult(
                        TextExplorationEventEntryResult.builder()
                                .content("***You gained " + EXPERIENCE_REWARD + " experience in the " + SkillType.FISTFIGHT.getName() + " skill.***")
                                .build()
                );
            }
        } else if (stage == DONT_GIVE_COIN_STAGE) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("\"I'm sorry.\" You barely have enough coin for yourself let alone a complete stranger. How will you survive if you give away all of your money?")
                            .build()
            ).addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("\"Be safe. These roads are treacherous even during the day,\" the man warns.")
                            .build()
            ).addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("\"I will.\" You turn with a hint of guilt and carry on your way.")
                            .build()
            );
        }

        userEntity.resetActiveExploration();

        return explorationResult;
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        if (stage == WELCOME_STAGE) {
            explorationResult.addEventEntryResult(
                    TextExplorationEventEntryResult.builder()
                            .content("\"May you spare coin for a hapless, old soul?\"")
                            .build()
            ).addEventEntryResult(
                    OptionExplorationEventEntryResult.builder()
                            .options(Lists.newArrayList(
                                    EventOption.builder()
                                            .text("Give the man 2 bronze coins.")
                                            .optionId(GIVE_COIN_STAGE)
                                            .build(),
                                    EventOption.builder()
                                            .text("Don't give him anything.")
                                            .optionId(DONT_GIVE_COIN_STAGE)
                                            .build()
                            ))
                            .build()
            );
        }

        return explorationResult;
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        if (stage == WELCOME_STAGE && (nextStage == GIVE_COIN_STAGE || nextStage == DONT_GIVE_COIN_STAGE)) {
            return true;
        }

        return false;
    }
}
