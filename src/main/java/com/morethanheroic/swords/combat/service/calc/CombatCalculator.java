package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.combat.domain.*;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.calc.drop.DropCalculator;
import com.morethanheroic.swords.combat.service.calc.scavenge.ScavengeCalculator;
import com.morethanheroic.swords.combat.service.calc.turn.TurnCalculatorFactory;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.service.SkillManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import com.morethanheroic.swords.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final DropCalculator dropCalculator;
    private final ScavengeCalculator scavengeCalculator;
    private final ItemDefinitionManager itemDefinitionManager;
    private final MapManager mapManager;
    private final InventoryManager inventoryManager;
    private final TurnCalculatorFactory turnCalculatorFactory;
    private final SkillManager skillManager;
    private final JournalManager journalManager;
    private final UserMapper userMapper;

    @Autowired
    public CombatCalculator(TurnCalculatorFactory turnCalculatorFactory, CombatMessageBuilder combatMessageBuilder, DropCalculator dropCalculator, ScavengeCalculator scavengeCalculator, ItemDefinitionManager itemDefinitionManager, MapManager mapManager, InventoryManager inventoryManager, SkillManager skillManager, JournalManager journalManager, UserMapper userMapper) {
        this.turnCalculatorFactory = turnCalculatorFactory;
        this.combatMessageBuilder = combatMessageBuilder;
        this.dropCalculator = dropCalculator;
        this.scavengeCalculator = scavengeCalculator;
        this.itemDefinitionManager = itemDefinitionManager;
        this.mapManager = mapManager;
        this.inventoryManager = inventoryManager;
        this.skillManager = skillManager;
        this.journalManager = journalManager;
        this.userMapper = userMapper;
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

            //Add scavenge
            ArrayList<Scavenge> scavengedItems = scavengeCalculator.calculateScavenge(combat.getMonsterCombatEntity().getMonsterDefinition());

            for (Scavenge scavenge : scavengedItems) {
                result.addMessage(combatMessageBuilder.buildDropMessage(itemDefinitionManager.getItemDefinition(scavenge.getItem()).getName(), scavenge.getAmount()));

                inventory.addItem(scavenge.getItem(), scavenge.getAmount());
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

            userMapper.updateBasicCombatStats(combat.getUserCombatEntity().getUserEntity().getId(), combat.getUserCombatEntity().getActualHealth(), combat.getUserCombatEntity().getActualMana(), combat.getUserCombatEntity().getUserEntity().getMovement() - 1);
        }
    }
}
