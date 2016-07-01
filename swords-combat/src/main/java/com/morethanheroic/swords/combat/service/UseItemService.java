package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UseItemService {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final CombatEffectApplierService combatEffectApplierService;
    private final InventoryFacade inventoryFacade;

    public boolean canUseItem(UserEntity userEntity, ItemDefinition item) {
        return inventoryFacade.getInventory(userEntity).hasItem(item.getId());
    }

    public void useItem(UserCombatEntity combatEntity, Combat combat, CombatResult combatResult, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseItem(combatEntity.getUserEntity(), item)) {
            applyItem(combatEntity, combat, combatResult, item, combatEffectDataHolder);
        }
    }

    public void useItem(UserEntity userEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        if (canUseItem(userEntity, item)) {
            applyItem(userEntity, item, combatEffectDataHolder);
        }
    }

    //TODO: merge the two applyItem together somehow!
    private void applyItem(CombatEntity combatEntity, Combat combat, CombatResult combatResult, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        final CombatEffectApplyingContext effectApplyingContext = CombatEffectApplyingContext.builder()
                                                                                             .source(new CombatEffectTarget(combatEntity))
                                                                                             .destination(new CombatEffectTarget(combatEntity))
                                                                                             .combatResult(combatResult)
                                                                                             .build();

        combatEffectApplierService.applyEffects(effectApplyingContext,(List) item.getCombatEffects(), combatEffectDataHolder);
    }

    private void applyItem(UserEntity userEntity, ItemDefinition item, CombatEffectDataHolder combatEffectDataHolder) {
        final UserCombatEntity userCombatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);
        final CombatResult combatResult = new CombatResult();
        final Combat combat = new Combat(userEntity, new MonsterDefinition.MonsterDefinitionBuilder().build(), globalAttributeCalculator);

        final CombatEffectApplyingContext effectApplyingContext = CombatEffectApplyingContext.builder()
                                                                                             .source(new CombatEffectTarget(userCombatEntity))
                                                                                             .destination(new CombatEffectTarget(userCombatEntity))
                                                                                             .combatResult(combatResult)
                                                                                             .build();

        combatEffectApplierService.applyEffects(effectApplyingContext, (List) item.getCombatEffects(), combatEffectDataHolder);

        userEntity.setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userEntity.getMovementPoints());
    }
}
