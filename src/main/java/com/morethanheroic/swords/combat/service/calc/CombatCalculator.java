package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.SimpleTurnCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.ZeroTurnCalculator;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final DropCalculator dropCalculator;
    private final ItemDefinitionManager itemDefinitionManager;
    private final MapManager mapManager;
    private final InventoryManager inventoryManager;
    private final SimpleTurnCalculator simpleTurnCalculator;
    private final ZeroTurnCalculator zeroTurnCalculator;

    @Autowired
    public CombatCalculator(CombatMessageBuilder combatMessageBuilder, DropCalculator dropCalculator, ItemDefinitionManager itemDefinitionManager, MapManager mapManager, InventoryManager inventoryManager, SimpleTurnCalculator simpleTurnCalculator, ZeroTurnCalculator zeroTurnCalculator) {
        this.combatMessageBuilder = combatMessageBuilder;
        this.dropCalculator = dropCalculator;
        this.itemDefinitionManager = itemDefinitionManager;
        this.mapManager = mapManager;
        this.inventoryManager = inventoryManager;
        this.simpleTurnCalculator = simpleTurnCalculator;
        this.zeroTurnCalculator = zeroTurnCalculator;
    }

    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition, MapObjectDatabaseEntity spawn) {
        CombatResult result = new CombatResult();
        Combat combat = new Combat(userEntity, monsterDefinition);

        startFight(result, combat);
        calculateFight(result, combat);
        endFight(result, combat, spawn);

        return result;
    }

    private void startFight(CombatResult result, Combat combat) {
        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(combat.getMonsterDefinition().getName()));
    }

    private void calculateFight(CombatResult result, Combat combat) {
        while (combat.getPlayerHealth() > 0 && combat.getMonsterHealth() > 0) {
            takeTurn(result, combat);
        }
    }

    public void takeTurn(CombatResult result, Combat combat) {
        if (combat.getTurn() == 0) {
            zeroTurnCalculator.takeTurn(result, combat);
        } else {
            simpleTurnCalculator.takeTurn(result, combat);
        }

        combat.increaseTurn();
    }

    private void endFight(CombatResult result, Combat combat, MapObjectDatabaseEntity spawn) {
        if (result.getWinner() == Winner.PLAYER) {
            //Add drops
            ArrayList<Drop> drops = dropCalculator.calculateDrop(combat.getMonsterDefinition());

            InventoryEntity inventory = inventoryManager.getInventory(combat.getUserEntity());

            for (Drop drop : drops) {
                result.addMessage(combatMessageBuilder.buildDropMessage(itemDefinitionManager.getItemDefinition(drop.getItem()).getName(), drop.getAmount()));

                inventory.addItem(drop.getItem(), drop.getAmount());
            }

            //Remove spawn
            mapManager.getMap(combat.getUserEntity().getMapId()).removeSpawn(spawn.getId());

            //Add xp
        }
    }
}
