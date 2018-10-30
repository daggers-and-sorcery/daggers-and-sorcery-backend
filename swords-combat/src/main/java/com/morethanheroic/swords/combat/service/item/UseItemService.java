package com.morethanheroic.swords.combat.service.item;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.entry.domain.cause.ItemCombatEffectCause;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.service.item.domain.ItemUsageContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.inventory.service.InventoryManipulator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This service is responsible for the item usage logic in the game.
 */
@Service
@RequiredArgsConstructor
public class UseItemService {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatEffectApplierService combatEffectApplierService;
    private final InventoryManipulator inventoryManipulator;
    private final CombatMessageFactory combatMessageFactory;

    /**
     * Check if the user can use an item or not. An item is usable for a given user if the user has it in his inventory.
     *
     * @param userEntity     the user that check the usability
     * @param itemDefinition the item to check the usability for
     * @return true if the user can use the item and false otherwise
     */
    public boolean canUseItem(final UserEntity userEntity, final ItemDefinition itemDefinition) {
        return inventoryManipulator.hasItem(userEntity, itemDefinition);
    }

    public void useItem(final UserEntity userEntity, final ItemDefinition item,
            final ItemUsageContext itemUsageContext) {
        if (canUseItem(userEntity, item)) {
            applyItem(userEntity, item, itemUsageContext);
        }
    }

    public List<CombatStep> useItem(final UserCombatEntity combatEntity, final ItemDefinition item,
            final ItemUsageContext itemUsageContext) {
        final List<CombatStep> combatSteps = new ArrayList<>();

        if (canUseItem(combatEntity.getUserEntity(), item)) {
            combatSteps.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("spell", "COMBAT_MESSAGE_ITEM_USED", item.getName()))
                            .build()
            );

            applyItem(combatEntity, item, itemUsageContext);

            combatEntity.getUserEntity().setBasicStats(combatEntity.getActualHealth(), combatEntity.getActualMana(), combatEntity.getUserEntity().getMovementPoints());
        } else {
            combatSteps.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("spell", "COMBAT_MESSAGE_CANT_USE_ITEM", item.getName()))
                            .build()
            );
        }

        return combatSteps;
    }

    private void applyItem(final UserEntity userEntity, final ItemDefinition item,
            final ItemUsageContext itemUsageContext) {
        final UserCombatEntity combatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);

        applyItem(combatEntity, item, itemUsageContext);

        userEntity.setBasicStats(combatEntity.getActualHealth(), combatEntity.getActualMana(), combatEntity.getActualMovement());
    }

    private void applyItem(final CombatEntity combatEntity, final ItemDefinition item,
            final ItemUsageContext itemUsageContext) {
        final List<CombatEffectApplyingContext> contexts = createContext(combatEntity, item, itemUsageContext);

        combatEffectApplierService.applyEffects(contexts,
                new CombatEffectDataHolder((Map) itemUsageContext.getParameters(), itemUsageContext.getSessionEntity()));
    }

    private List<CombatEffectApplyingContext> createContext(final CombatEntity combatEntity, final ItemDefinition item,
            final ItemUsageContext itemUsageContext) {
        return item.getCombatEffects().stream()
                .map(effectSettingDefinitionHolder -> CombatEffectApplyingContext.builder()
                        .source(new CombatEffectTarget(combatEntity))
                        .destination(new CombatEffectTarget(combatEntity))
                        .combatSteps(Lists.newArrayList())
                        .effectSettings(effectSettingDefinitionHolder)
                        .sessionEntity(itemUsageContext.getSessionEntity())
                        .parameters((Map) itemUsageContext.getParameters())
                        .cause(new ItemCombatEffectCause(item))
                        .build()
                )
                .collect(Collectors.toList());
    }
}
