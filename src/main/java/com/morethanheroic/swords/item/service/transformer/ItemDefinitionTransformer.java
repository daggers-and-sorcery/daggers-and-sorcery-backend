package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.effect.service.EffectDefinitionBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.ItemEffect;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDefinitionTransformer {

    private final EffectDefinitionBuilder effectDefinitionBuilder;

    @Autowired
    public ItemDefinitionTransformer(EffectDefinitionBuilder effectDefinitionBuilder) {
        this.effectDefinitionBuilder = effectDefinitionBuilder;
    }

    public ItemDefinition transform(RawItemDefinition rawItemDefinition) throws Exception {
        ItemDefinition.ItemDefinitionBuilder itemDefinitionBuilder = new ItemDefinition.ItemDefinitionBuilder();

        itemDefinitionBuilder.setId(rawItemDefinition.getId());
        itemDefinitionBuilder.setName(rawItemDefinition.getName());
        itemDefinitionBuilder.setType(rawItemDefinition.getType());
        itemDefinitionBuilder.setUsable(rawItemDefinition.isUsable());
        itemDefinitionBuilder.setWeight(rawItemDefinition.getWeight());
        itemDefinitionBuilder.setEquipment(rawItemDefinition.isEquipment());

        itemDefinitionBuilder.setCombatEffects(buildEffects(rawItemDefinition.getEffectList()));

        itemDefinitionBuilder.setBasicModifiers(rawItemDefinition.getBasicModifiers());
        itemDefinitionBuilder.setCombatModifiers(rawItemDefinition.getCombatModifiers());
        itemDefinitionBuilder.setGeneralModifiers(rawItemDefinition.getGeneralModifiers());
        itemDefinitionBuilder.setSkillModifiers(rawItemDefinition.getSkillModifiers());

        itemDefinitionBuilder.setBasicRequirements(rawItemDefinition.getBasicRequirements());
        itemDefinitionBuilder.setCombatRequirements(rawItemDefinition.getCombatRequirements());
        itemDefinitionBuilder.setGeneralRequirements(rawItemDefinition.getGeneralRequirements());
        itemDefinitionBuilder.setSkillRequirements(rawItemDefinition.getSkillRequirements());

        return itemDefinitionBuilder.build();
    }

    private List<CombatEffect> buildEffects(List<ItemEffect> rawEffectList) throws Exception {
        List<CombatEffect> effects = new ArrayList<>();

        if(rawEffectList != null) {
            for (ItemEffect effect : rawEffectList) {
                effects.add(effectDefinitionBuilder.build(effect));
            }
        }

        return effects;
    }
}
