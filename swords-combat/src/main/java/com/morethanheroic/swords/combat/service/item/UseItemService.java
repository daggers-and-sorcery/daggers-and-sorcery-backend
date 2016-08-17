package com.morethanheroic.swords.combat.service.item;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UseItemService {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatEffectApplierService combatEffectApplierService;
    private final InventoryEntityFactory inventoryEntityFactory;

    public boolean canUseItem(UserEntity userEntity, ItemDefinition item) {
        return inventoryEntityFactory.getEntity(userEntity.getId()).hasItem(item);
    }

    public List<CombatStep> useItem(UserCombatEntity combatEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        final List<CombatStep> combatSteps = new ArrayList<>();

        if (canUseItem(combatEntity.getUserEntity(), item)) {
            applyItem(combatEntity, item, combatEffectDataHolder);

            //TODO: add item used combat steps
        } else {
            //TODO: add can't use item combat steps
        }

        return combatSteps;
    }

    public void useItem(UserEntity userEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseItem(userEntity, item)) {
            applyItem(userEntity, item, combatEffectDataHolder);
        }
    }

    //TODO: merge the two applyItem together somehow!
    private void applyItem(CombatEntity combatEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        final List<CombatEffectApplyingContext> contexts = new ArrayList<>();
        for (EffectSettingDefinitionHolder effectSettingDefinitionHolder : item.getCombatEffects()) {
            contexts.add(CombatEffectApplyingContext.builder()
                    .source(new CombatEffectTarget(combatEntity))
                    .destination(new CombatEffectTarget(combatEntity))
                    .combatSteps(Lists.newArrayList())
                    .effectSettings(effectSettingDefinitionHolder)
                    .build()
            );
        }

        combatEffectApplierService.applyEffects(contexts, combatEffectDataHolder);
    }

    private void applyItem(UserEntity userEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        final UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);

        final List<CombatEffectApplyingContext> contexts = new ArrayList<>();
        for (EffectSettingDefinitionHolder effectSettingDefinitionHolder : item.getCombatEffects()) {
            contexts.add(CombatEffectApplyingContext.builder()
                    .source(new CombatEffectTarget(userCombatEntity))
                    .destination(new CombatEffectTarget(userCombatEntity))
                    .combatSteps(Lists.newArrayList())
                    .effectSettings(effectSettingDefinitionHolder)
                    .build()
            );
        }

        combatEffectApplierService.applyEffects(contexts, combatEffectDataHolder);

        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints());
    }
}
