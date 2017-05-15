package com.morethanheroic.swords.explore.service.event.newevent;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.combat.service.drop.DropAdder;
import com.morethanheroic.swords.combat.service.drop.DropTextCreator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.AttributeExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.OptionExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.option.EventOption;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.MessageBoxMessageEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.MessageEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.QuestEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.AttributeAttemptEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.domain.AttributeAttemptEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.loot.service.cache.LootDefinitionCache;
import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExplorationResultBuilder {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    @Autowired
    private MessageEventEntryEvaluator messageEventEntryEvaluator;

    @Autowired
    private CombatEventEntryEvaluator combatEventEntryEvaluator;

    @Autowired
    private MessageBoxMessageEventEntryEvaluator messageBoxMessageEventEntryEvaluator;

    @Autowired
    private AttributeAttemptEventEntryEvaluator attributeAttemptEventEntryEvaluator;

    @Autowired
    private CombatCalculator combatCalculator;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private InventoryEntityFactory inventoryEntityFactory;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private MultiWayExplorationResultBuilderFactory multiWayExplorationResultBuilderFactory;

    @Autowired
    private LootDefinitionCache lootDefinitionCache;

    @Autowired
    private DropCalculator dropCalculator;

    @Autowired
    private DropTextCreator dropTextCreator;

    @Autowired
    private DropAdder dropAdder;

    @Autowired
    private QuestEventEntryEvaluator questEventEntryEvaluator;

    private ExplorationResult explorationResult;
    private UserEntity userEntity;

    public ExplorationResultBuilder initialize(final UserEntity userEntity, final ExplorationResult explorationResult) {
        this.userEntity = userEntity;
        this.explorationResult = explorationResult;

        return this;
    }

    public ExplorationResultBuilder setEventStage(final int eventId, final int stage) {
        userEntity.setActiveExploration(eventId, stage);

        return this;
    }

    @Transactional
    public ExplorationResultBuilder resetExploration() {
        userEntity.resetActiveExploration();

        //// FIXME: 9/22/2016 This is a hack, this should be handled in the exploration events correctly
        combatCalculator.removeAllCombatForUser(userEntity);

        return this;
    }

    public ExplorationResultBuilder newMessageBoxEntry(final String messageId, final Object... args) {
        explorationResult.addEventEntryResult(
                messageBoxMessageEventEntryEvaluator.messageEntry(messageId, args)
        );

        return this;
    }

    public ExplorationResultBuilder newMessageEntry(final String messageId, final Object... args) {
        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry(messageId, args)
        );

        return this;
    }

    public ExplorationResultBuilder newQuestEntry(final QuestDefinition questDefinition, final int acceptQuestStage, final int declineQuestStage) {
        explorationResult.addEventEntryResult(
                questEventEntryEvaluator.questEntry(questDefinition.getName(), questDefinition.getDefinition(), acceptQuestStage, declineQuestStage)
        );

        return this;
    }

    public ExplorationResultBuilder newCombatEntry(final int opponentId, final int eventId, final int stage) {
        final CombatEventEntryEvaluatorResult combatEventEntryEvaluatorResult = combatEventEntryEvaluator.calculateCombat(userEntity, combatEventEntryEvaluator.convertMonsterIdToDefinition(opponentId));

        explorationResult.addEventEntryResult(combatEventEntryEvaluatorResult.getResult());

        if (!combatEventEntryEvaluatorResult.getResult().isPlayerDead()) {
            userEntity.setActiveExploration(eventId, stage);
        }

        return this;
    }

    public ExplorationResultBuilder newOptionEntry(final ReplyOption... replyOptions) {
        explorationResult.addEventEntryResult(
                OptionExplorationEventEntryResult.builder()
                        .options(
                                Arrays.stream(replyOptions)
                                        .map(
                                                option ->
                                                        EventOption.builder()
                                                                .text(messageSource.getMessage(option.getMessage(), new Object[]{}, DEFAULT_LOCALE))
                                                                .optionId(option.getStage())
                                                                .build()
                                        )
                                        .collect(Collectors.toList())
                        )
                        .build()
        );

        return this;
    }

    public ExplorationResultBuilder continueCombatEntry() {
        final CombatMessage combatMessage = new CombatMessage();
        combatMessage.addData("message", "Continue fighting.");

        explorationResult.addEventEntryResult(
                CombatEventEntryEvaluatorResult.builder()
                        .result(
                                CombatExplorationEventEntryResult.builder()
                                        .combatSteps(
                                                Lists.newArrayList(
                                                        DefaultCombatStep.builder()
                                                                .message(combatMessage)
                                                                .build()
                                                )
                                        )
                                        .combatEnded(!combatCalculator.isCombatRunning(userEntity))
                                        .playerDead(false)
                                        .build()
                        )
                        .build()
                        .getResult()
        );

        return this;
    }

    //TODO: move the content of this bs to somewhere
    public ExplorationResultBuilder newLootEntry(final int lootId, final String messageId, final Object... messageArgs) {
        final List<Drop> chestDrops = dropCalculator.calculateDrops(lootDefinitionCache.getDefinition(lootId).getDropDefinitions());

        //Award the drops
        dropAdder.addDrops(userEntity, chestDrops);

        //Print the drops
        final List<Object> finalMessageArgs = new ArrayList<>();
        finalMessageArgs.add(dropTextCreator.listAsText(chestDrops));
        finalMessageArgs.addAll(Arrays.asList(messageArgs));

        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry(messageId, finalMessageArgs.toArray())
        );

        return this;
    }

    public MultiWayExplorationResultBuilder newAttributeAttemptEntry(final Attribute attribute, final int valueToHit) {
        final AttributeAttemptEventEntryEvaluatorResult attemptResult = attributeAttemptEventEntryEvaluator.attributeAttempt(userEntity, attribute, valueToHit);

        explorationResult.addEventEntryResult(
                AttributeExplorationEventEntryResult.builder()
                        .result(attemptResult.getResult())
                        .build()
        );

        return new MultiWayExplorationResultBuilder(this, attemptResult.isSuccessful());
    }

    public MultiWayExplorationResultBuilder newHasItemMultiWayPath(final ExplorationContext explorationContext, final int... items) {
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(explorationContext.getUserEntity().getId());

        for (int item : items) {
            if (!inventoryEntity.hasItem(itemDefinitionCache.getDefinition(item))) {
                return multiWayExplorationResultBuilderFactory.newFailureBasedMultiWayExplorationResultBuilder(this);
            }
        }

        return multiWayExplorationResultBuilderFactory.newSuccessBasedMultiWayExplorationResultBuilder(this);
    }

    public MultiWayExplorationResultBuilder newCustomMultiWayPath(final Callable<Boolean> calculateSuccess) {
        try {
            return multiWayExplorationResultBuilderFactory.newMultiWayExplorationResultBuilder(this, calculateSuccess.call());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MultiWayExplorationResultBuilder newIsCombatRunningMultiWayPath(final ExplorationContext explorationContext) {
        return combatCalculator.isCombatRunning(explorationContext.getUserEntity()) ?
                multiWayExplorationResultBuilderFactory.newSuccessBasedMultiWayExplorationResultBuilder(this) :
                multiWayExplorationResultBuilderFactory.newFailureBasedMultiWayExplorationResultBuilder(this);
    }

    public synchronized ExplorationResultBuilder newCustomLogicEntry(final Runnable runnable) {
        runnable.run();

        return this;
    }

    public synchronized ExplorationResult build() {
        return explorationResult;
    }
}
