package com.morethanheroic.swords.combat.service.adder;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Scavenge;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.scavenge.ScavengingCalculator;
import com.morethanheroic.swords.combatsettings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScavengingAdder {

    @Autowired
    private SettingsMapper settingsMapper;

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private ItemDefinitionManager itemDefinitionManager;

    @Autowired
    private InventoryManager inventoryManager;

    public void addScavengingDropsToUserFromMonsterDefinition(CombatResult result, UserEntity user, MonsterDefinition monster) {
        if (settingsMapper.getSettings(user.getId()).isScavengingEnabled() && user.getScavengingPoint() > 0) {
            user.setScavengingPoint(user.getScavengingPoint() - 1);

            InventoryEntity inventory = inventoryManager.getInventory(user);

            //Add scavenge
            ArrayList<Scavenge> scavengedItems = scavengingCalculator.calculateScavenge(monster);

            for (Scavenge scavenge : scavengedItems) {
                result.addMessage(combatMessageBuilder.buildScavengeMessage(itemDefinitionManager.getItemDefinition(scavenge.getItem()).getName(), scavenge.getAmount()));

                inventory.addItem(scavenge.getItem(), scavenge.getAmount());
            }
        }
    }
}
