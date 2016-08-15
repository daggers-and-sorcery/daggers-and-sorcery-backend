package com.morethanheroic.swords.combat.service.awarder;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DropAwarder {

    @NonNull
    private final DropCalculator dropCalculator;

    @NonNull
    private final CombatMessageBuilder combatMessageBuilder;

    @NonNull
    private final InventoryFacade inventoryFacade;

    public List<CombatStep> addDropsToUserFromMonsterDefinition( UserEntity userEntity, MonsterDefinition monster) {
        final ArrayList<CombatStep> result = new ArrayList<>();

        final ArrayList<Drop> drops = dropCalculator.calculateDrop(monster);

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        for (Drop drop : drops) {
            if (drop.isIdentified()) {
                result.add(
                        DefaultCombatStep.builder()
                                .message(combatMessageBuilder.buildDropMessage(drop.getItem().getName(), drop.getAmount()))
                                .build()
                );
            } else {
                result.add(
                        DefaultCombatStep.builder()
                                .message(combatMessageBuilder.buildDropMessage("Unidentified item", drop.getAmount()))
                                .build()
                );
            }

            inventoryEntity.addItem(drop.getItem(), drop.getAmount(), drop.isIdentified());
        }

        return result;
    }

    @Deprecated
    public void addDropsToUserFromMonsterDefinition(CombatResult result, UserEntity userEntity, MonsterDefinition monster) {
        final List<Drop> drops = dropCalculator.calculateDropsForMonster(monster);

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(userEntity);

        for (Drop drop : drops) {
            if (drop.isIdentified()) {
                result.addMessage(combatMessageBuilder.buildDropMessage(drop.getItem().getName(), drop.getAmount()));
            } else {
                result.addMessage(combatMessageBuilder.buildDropMessage("Unidentified item", drop.getAmount()));
            }

            inventoryEntity.addItem(drop.getItem(), drop.getAmount(), drop.isIdentified());
        }
    }
}
