package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.TurnCalculator;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final TurnCalculator turnCalculator;
    private final DropCalculator dropCalculator;
    private final ItemDefinitionManager itemDefinitionManager;

    @Autowired
    public CombatCalculator(TurnCalculator turnCalculator, CombatMessageBuilder combatMessageBuilder, DropCalculator dropCalculator, ItemDefinitionManager itemDefinitionManager) {
        this.turnCalculator = turnCalculator;
        this.combatMessageBuilder = combatMessageBuilder;
        this.dropCalculator = dropCalculator;
        this.itemDefinitionManager = itemDefinitionManager;
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
            turnCalculator.takeTurn(result, combat);
        }
    }

    private void endFight(CombatResult result, Combat combat, MapObjectDatabaseEntity spawn) {
        if (result.getWinner() == Winner.PLAYER) {
            //Add drops
            ArrayList<Drop> drops = dropCalculator.calculateDrop(combat.getMonsterDefinition());

            for (Drop drop : drops) {
                result.addMessage(combatMessageBuilder.buildDropMessage(itemDefinitionManager.getItemDefinition(drop.getItem()).getName(), drop.getAmount()));

                combat.getUserEntity().getInventory().addItem(drop.getItem(), drop.getAmount());
            }

            //Remove spawn
            combat.getUserEntity().getMap().removeSpawn(spawn.getId());

            //Add xp
        }
    }
}
