package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.adder.DropAdder;
import com.morethanheroic.swords.combat.service.adder.ScavengingAwarder;
import com.morethanheroic.swords.combat.service.adder.XpAdder;
import com.morethanheroic.swords.combat.service.calc.scavenge.ScavengingCalculator;
import com.morethanheroic.swords.combat.service.calc.scavenge.domain.ScavengingResult;
import com.morethanheroic.swords.combat.service.calc.turn.TurnCalculatorFactory;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.skill.domain.ScavengingEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final TurnCalculatorFactory turnCalculatorFactory;
    private final JournalManager journalManager;
    private final UserMapper userMapper;

    @Autowired
    private DropAdder dropAdder;

    @Autowired
    private ScavengingAwarder scavengingAwarder;

    @Autowired
    private XpAdder xpAdder;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private ScavengingCalculator scavengingCalculator;

    @Autowired
    public CombatCalculator(TurnCalculatorFactory turnCalculatorFactory, CombatMessageBuilder combatMessageBuilder, MapManager mapManager, JournalManager journalManager, UserMapper userMapper) {
        this.turnCalculatorFactory = turnCalculatorFactory;
        this.combatMessageBuilder = combatMessageBuilder;
        this.journalManager = journalManager;
        this.userMapper = userMapper;
    }

    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition, MapObjectDatabaseEntity spawn) {
        CombatResult result = new CombatResult();
        Combat combat = new Combat(userEntity, monsterDefinition, globalAttributeCalculator);

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
            UserEntity user = combat.getUserCombatEntity().getUserEntity();
            MonsterDefinition monster = combat.getMonsterCombatEntity().getMonsterDefinition();

            dropAdder.addDropsToUserFromMonsterDefinition(result, user, monster);

            //TODO: moce these somewhere else? Eg create a bean for it?
            SettingsEntity settingsEntity = user.getSettings();
            ScavengingEntity scavengingEntity = user.getSkills().getScavenging();
            SkillEntity skillEntity = user.getSkills();
            InventoryEntity inventoryEntity = user.getInventory();
            if (shouldScavenge(settingsEntity, scavengingEntity)) {
                ScavengingResult scavengingResult = scavengingCalculator.calculateScavenge(skillEntity, monster);

                scavengingAwarder.awardScavengingResultToUser(result, skillEntity, inventoryEntity, scavengingResult);

                decreaseUserScavengingPoints(scavengingEntity);
            }

            xpAdder.addXpToUserFromMonsterDefinition(result, user);

            user.getMovement().getMap().removeSpawn(spawn.getId());

            //TODO: Move user mapper outside, this class shouldn't know about user mapper at all, it should know more about user facade
            userMapper.updateBasicCombatStats(user.getId(), combat.getUserCombatEntity().getActualHealth(), combat.getUserCombatEntity().getActualMana(), user.getRegeneration().getMovementPoints() - 1);
        }
    }

    private void decreaseUserScavengingPoints(ScavengingEntity scavengingEntity) {
        scavengingEntity.setScavengingPoint(scavengingEntity.getScavengingPoint() - 1);
    }

    private boolean shouldScavenge(SettingsEntity settingsEntity, ScavengingEntity scavengingEntity) {
        return settingsEntity.isScavengingEnabled() && scavengingEntity.getScavengingPoint() > 0;
    }
}
