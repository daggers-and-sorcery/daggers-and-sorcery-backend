package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.loot.domain.DropDefinition;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.MonsterType;
import com.morethanheroic.swords.monster.domain.ScavengingDefinition;
import com.morethanheroic.swords.monster.domain.WeaponType;
import com.morethanheroic.swords.monster.service.loader.domain.RawDropDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawMonsterDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawScavengingDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonsterDefinitionTransformer {

    private final DiceAttributeTransformer diceAttributeTransformer;
    private final DropDefinitionTransformer dropDefinitionTransformer;
    private final ScavengingDefinitionTransformer scavengingDefinitionTransformer;

    public MonsterDefinition transform(RawMonsterDefinition rawMonsterDefinition) {
        MonsterDefinition.MonsterDefinitionBuilder monsterDefinitionBuilder = new MonsterDefinition.MonsterDefinitionBuilder();

        monsterDefinitionBuilder.setAiming(diceAttributeTransformer.transform(rawMonsterDefinition.getAiming()));
        monsterDefinitionBuilder.setRangedDamage(diceAttributeTransformer.transform(rawMonsterDefinition.getRangedDamage()));
        monsterDefinitionBuilder.setAttack(diceAttributeTransformer.transform(rawMonsterDefinition.getAttack()));
        monsterDefinitionBuilder.setDamage(diceAttributeTransformer.transform(rawMonsterDefinition.getDamage()));
        monsterDefinitionBuilder.setInitiation(diceAttributeTransformer.transform(rawMonsterDefinition.getInitiation()));
        monsterDefinitionBuilder.setMagicAttack(diceAttributeTransformer.transform(rawMonsterDefinition.getMagicAttack()));
        monsterDefinitionBuilder.setMagicDamage(diceAttributeTransformer.transform(rawMonsterDefinition.getMagicDamage()));

        monsterDefinitionBuilder.setDefense(rawMonsterDefinition.getDefense());
        monsterDefinitionBuilder.setSpellResistance(rawMonsterDefinition.getSpellResistance());

        monsterDefinitionBuilder.setId(rawMonsterDefinition.getId());
        monsterDefinitionBuilder.setName(rawMonsterDefinition.getName());
        monsterDefinitionBuilder.setAttackType(rawMonsterDefinition.getAttackType());
        monsterDefinitionBuilder.setHealth(rawMonsterDefinition.getHealth());
        monsterDefinitionBuilder.setMana(rawMonsterDefinition.getMana());
        monsterDefinitionBuilder.setLevel(rawMonsterDefinition.getLevel());

        monsterDefinitionBuilder.setType(MonsterType.valueOf(rawMonsterDefinition.getType()));

        if (rawMonsterDefinition.getSubtype() != null) {
            monsterDefinitionBuilder.setSubtype(MonsterType.valueOf(rawMonsterDefinition.getSubtype()));
        }

        if (rawMonsterDefinition.getWeapontype() != null) {
            monsterDefinitionBuilder.setWeaponType(WeaponType.valueOf(rawMonsterDefinition.getWeapontype()));
        }

        monsterDefinitionBuilder.setDropDefinitions(transformDropDefinitions(rawMonsterDefinition.getDropDefinitions()));
        monsterDefinitionBuilder.setScavengeDefinitions(transformScavengingDefinitions(rawMonsterDefinition.getScavengeDefinitions()));

        return monsterDefinitionBuilder.build();
    }

    private List<DropDefinition> transformDropDefinitions(List<RawDropDefinition> rawDropDefinitions) {
        return rawDropDefinitions.stream().map(dropDefinitionTransformer::transform).collect(Collectors.toList());
    }

    private List<ScavengingDefinition> transformScavengingDefinitions(List<RawScavengingDefinition> rawDropDefinitions) {
        return rawDropDefinitions.stream().map(scavengingDefinitionTransformer::transform).collect(Collectors.toList());
    }
}
