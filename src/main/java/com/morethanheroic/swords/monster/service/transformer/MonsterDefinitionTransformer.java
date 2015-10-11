package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.monster.domain.DropDefinition;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawDropDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawMonsterDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonsterDefinitionTransformer {

    private final DiceAttributeTransformer diceAttributeTransformer;
    private final DropDefinitionTransformer dropDefinitionTransformer;

    @Autowired
    public MonsterDefinitionTransformer(DiceAttributeTransformer diceAttributeTransformer, DropDefinitionTransformer dropDefinitionTransformer) {
        this.diceAttributeTransformer = diceAttributeTransformer;
        this.dropDefinitionTransformer = dropDefinitionTransformer;
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

        monsterDefinitionBuilder.setDropDefinitions(transformDropDefinitions(rawMonsterDefinition.getDropDefinitions()));

        return monsterDefinitionBuilder.build();
    }

    private List<DropDefinition> transformDropDefinitions(List<RawDropDefinition> rawDropDefinitions) {
        return rawDropDefinitions.stream().map(dropDefinitionTransformer::transform).collect(Collectors.toList());
    }
}
