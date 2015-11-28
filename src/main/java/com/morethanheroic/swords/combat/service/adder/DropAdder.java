package com.morethanheroic.swords.combat.service.adder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DropAdder {

    @Autowired
    private DropCalculator dropCalculator;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    public void addDropsToUserFromMonsterDefinition(CombatResult result, UserEntity user, MonsterDefinition monster) {
        ArrayList<Drop> drops = dropCalculator.calculateDrop(monster);

        InventoryEntity inventoryEntity = user.getInventory();

        for (Drop drop : drops) {
            if(drop.isIdentified()) {
                result.addMessage(combatMessageBuilder.buildDropMessage(drop.getItem().getName(), drop.getAmount()));
            } else {
                result.addMessage(combatMessageBuilder.buildDropMessage("Unidentified item", drop.getAmount()));
            }

            inventoryEntity.addItem(drop.getItem(), drop.getAmount(), drop.isIdentified());
        }
    }
}
