package com.morethanheroic.swords.combat.service.awarder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DropAwarder {

    private final DropCalculator dropCalculator;
    private final CombatMessageBuilder combatMessageBuilder;
    private final CombatMessageFactory combatMessageFactory;
    private final InventoryEntityFactory inventoryEntityFactory;

    public List<CombatStep> addDropsToUserFromMonsterDefinition(final UserEntity userEntity, final MonsterDefinition monster) {
        final ArrayList<CombatStep> result = new ArrayList<>();

        final List<Drop> drops = dropCalculator.calculateDropsForMonster(monster);

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

        for (Drop drop : drops) {
            if (drop.isIdentified()) {
                result.add(
                        DefaultCombatStep.builder()
                                .message(combatMessageFactory.newMessage("item", "COMBAT_MESSAGE_DROP", drop.getAmount(), drop.getItem().getName()))
                                .build()
                );
            } else {
                result.add(
                        DefaultCombatStep.builder()
                                .message(combatMessageFactory.newMessage("item", "COMBAT_MESSAGE_DROP", drop.getAmount(), "Unidentified item"))
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

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

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
