package com.morethanheroic.swords.monster.service;

import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawMonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterDefinitionTransformer {

    private final DiceAttributeTransformer diceAttributeTransformer;

    @Autowired
    public MonsterDefinitionTransformer(DiceAttributeTransformer diceAttributeTransformer) {
        this.diceAttributeTransformer = diceAttributeTransformer;
    }

    public MonsterDefinition transform(RawMonsterDefinition rawMonsterDefinition) {
        MonsterDefinition.MonsterDefinitionBuilder monsterDefinitionBuilder = new MonsterDefinition.MonsterDefinitionBuilder();

        monsterDefinitionBuilder.setAiming(diceAttributeTransformer.transform(rawMonsterDefinition.getAiming()));
        monsterDefinitionBuilder.setRangedDamage(diceAttributeTransformer.transform(rawMonsterDefinition.getRangedDamage()));
        monsterDefinitionBuilder.setAttack(diceAttributeTransformer.transform(rawMonsterDefinition.getAttack()));
        monsterDefinitionBuilder.setDamage(diceAttributeTransformer.transform(rawMonsterDefinition.getDamage()));
        monsterDefinitionBuilder.setInitiation(diceAttributeTransformer.transform(rawMonsterDefinition.getInitiation()));

        monsterDefinitionBuilder.setDefense(rawMonsterDefinition.getDefense());

        monsterDefinitionBuilder.setId(rawMonsterDefinition.getId());
        monsterDefinitionBuilder.setName(rawMonsterDefinition.getName());
        monsterDefinitionBuilder.setAttackType(rawMonsterDefinition.getAttackType());
        monsterDefinitionBuilder.setHealth(rawMonsterDefinition.getHealth());
        monsterDefinitionBuilder.setMana(rawMonsterDefinition.getMana());
        monsterDefinitionBuilder.setLevel(rawMonsterDefinition.getLevel());

        return monsterDefinitionBuilder.build();
    }
}
