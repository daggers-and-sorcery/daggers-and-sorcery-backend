package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.TurnCalculatorFactory;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.repository.domain.JournalMapper;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final DropCalculator dropCalculator;
    private final ItemDefinitionManager itemDefinitionManager;
    private final MapManager mapManager;
    private final InventoryManager inventoryManager;
    private final TurnCalculatorFactory turnCalculatorFactory;
    private final SkillManager skillManager;
    private final JournalManager journalManager;

    @Autowired
    public CombatCalculator(TurnCalculatorFactory turnCalculatorFactory, CombatMessageBuilder combatMessageBuilder, DropCalculator dropCalculator, ItemDefinitionManager itemDefinitionManager, MapManager mapManager, InventoryManager inventoryManager, SkillManager skillManager, JournalManager journalManager) {
        this.turnCalculatorFactory = turnCalculatorFactory;
        this.combatMessageBuilder = combatMessageBuilder;
        this.dropCalculator = dropCalculator;
        this.itemDefinitionManager = itemDefinitionManager;
        this.mapManager = mapManager;
        this.inventoryManager = inventoryManager;
        this.skillManager = skillManager;
        this.journalManager = journalManager;
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
        journalManager.createJournalEntry(combat.getUserCombatEntity().getUserEntity(), JournalType.MONSTER, combat.getMonsterCombatEntity().getMonsterDefinition().getId());

        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName()));
    }

    private void calculateFight(CombatResult result, Combat combat) {
        while (combat.getUserCombatEntity().getActualHealth() > 0 && combat.getMonsterCombatEntity().getActualHealth() > 0) {
            turnCalculatorFactory.getTurnCalculator(combat.getTurn()).takeTurn(result, combat);
        }
    }

    private void endFight(CombatResult result, Combat combat, MapObjectDatabaseEntity spawn) {
        if (result.getWinner() == Winner.PLAYER) {
            //Add drops
            ArrayList<Drop> drops = dropCalculator.calculateDrop(combat.getMonsterCombatEntity().getMonsterDefinition());

            InventoryEntity inventory = inventoryManager.getInventory(combat.getUserCombatEntity().getUserEntity());

            for (Drop drop : drops) {
                result.addMessage(combatMessageBuilder.buildDropMessage(itemDefinitionManager.getItemDefinition(drop.getItem()).getName(), drop.getAmount()));

                inventory.addItem(drop.getItem(), drop.getAmount());
            }

            //Remove spawn
            mapManager.getMap(combat.getUserCombatEntity().getUserEntity().getMapId()).removeSpawn(spawn.getId());

            //Add xp
            SkillEntity skillEntity = skillManager.getSkills(combat.getUserCombatEntity().getUserEntity());

            Map<SkillAttribute, Integer> rewardXpMap = result.getRewardXpMap();

            for (Map.Entry<SkillAttribute, Integer> rewardEntity : rewardXpMap.entrySet()) {
                result.addMessage(combatMessageBuilder.buildXpRewardMessage(rewardEntity.getKey().getName(), rewardEntity.getValue()));

                skillEntity.addSkillXp(rewardEntity.getKey(), rewardEntity.getValue());
            }
        }
    }
}
