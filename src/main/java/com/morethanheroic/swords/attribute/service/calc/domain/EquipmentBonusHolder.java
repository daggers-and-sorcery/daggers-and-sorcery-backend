package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.enums.Attribute;

import java.util.HashMap;

public class EquipmentBonusHolder {

    private HashMap<Attribute, Integer> attributeBonuses = new HashMap<>();

    public void addEquipmentBonus(Attribute attribute, int amount)  {
        if(!attributeBonuses.containsKey(attribute)) {
            attributeBonuses.put(attribute, amount);
        } else {
            attributeBonuses.put(attribute, attributeBonuses.get(attribute) + amount);
        }
    }

    public int getEquipmentBonus(Attribute attribute) {
        if(!attributeBonuses.containsKey(attribute)) {
            return 0;
        }

        return attributeBonuses.get(attribute);
    }
}
