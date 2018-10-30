package com.morethanheroic.swords.combat.service.item;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UseItemService {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatEffectApplierService combatEffectApplierService;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final CombatMessageFactory combatMessageFactory;

    public boolean canUseItem(final UserEntity userEntity, final ItemDefinition item) {
        return inventoryEntityFactory.getEntity(userEntity).hasItem(item);
    }

    public List<CombatStep> useItem(final UserCombatEntity combatEntity, final ItemDefinition item,
            final CombatEffectDataHolder combatEffectDataHolder) {
        final List<CombatStep> combatSteps = new ArrayList<>();

        if (canUseItem(combatEntity.getUserEntity(), item)) {
            combatSteps.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("spell", "COMBAT_MESSAGE_ITEM_USED", item.getName()))
                            .build()
            );

            applyItem(combatEntity, item, combatEffectDataHolder);

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

    public void useItem(final UserEntity userEntity, final ItemDefinition item,
            final CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseItem(userEntity, item)) {
            applyItem(userEntity, item, combatEffectDataHolder);
        }
    }

    private void applyItem(final UserEntity userEntity, final ItemDefinition item,
            final CombatEffectDataHolder combatEffectDataHolder) {
        final UserCombatEntity combatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);

        applyItem(combatEntity, item, combatEffectDataHolder);

        userEntity.setBasicStats(combatEntity.getActualHealth(), combatEntity.getActualMana(), combatEntity.getActualMovement());
    }

    private void applyItem(final CombatEntity combatEntity, final ItemDefinition item,
            final CombatEffectDataHolder combatEffectDataHolder) {
        final List<CombatEffectApplyingContext> contexts = createContext(combatEntity, item, combatEffectDataHolder);

        combatEffectApplierService.applyEffects(contexts, combatEffectDataHolder);
    }

    private List<CombatEffectApplyingContext> createContext(final CombatEntity combatEntity, final ItemDefinition item,
            final CombatEffectDataHolder combatEffectDataHolder) {
        return item.getCombatEffects().stream()
                .map(effectSettingDefinitionHolder -> CombatEffectApplyingContext.builder()
                        .source(new CombatEffectTarget(combatEntity))
                        .destination(new CombatEffectTarget(combatEntity))
                        .combatSteps(Lists.newArrayList())
                        .effectSettings(effectSettingDefinitionHolder)
                        .sessionEntity(combatEffectDataHolder.getSessionEntity())
                        .parameters(combatEffectDataHolder.getParameters())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
