package com.morethanheroic.swords.combat.service.adder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
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
    private InventoryManager inventoryManager;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private ItemDefinitionManager itemDefinitionManager;

    public void addDropsToUserFromMonsterDefinition(CombatResult result, UserEntity user, MonsterDefinition monster) {
        ArrayList<Drop> drops = dropCalculator.calculateDrop(monster);

        InventoryEntity inventory = inventoryManager.getInventory(user);

        for (Drop drop : drops) {
            result.addMessage(combatMessageBuilder.buildDropMessage(itemDefinitionManager.getItemDefinition(drop.getItem()).getName(), drop.getAmount()));

            inventory.addItem(drop.getItem(), drop.getAmount());
        }
    }
}
