package com.morethanheroic.swords.race.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Race {
    HUMAN, ORC, DWARF, ELF, DARK_ELF, LIZARDMEN, GNOME, DRACONIC;

    static {
        ORC.setRacialModifier(GeneralAttribute.STRENGTH, 20);
        ORC.setRacialModifier(GeneralAttribute.ENDURANCE, 10);
        ORC.setRacialModifier(GeneralAttribute.VITALITY, 10);
        ORC.setRacialModifier(GeneralAttribute.INTELLIGENCE, -30);
        ORC.setRacialModifier(GeneralAttribute.WISDOM, -10);
        ORC.setRacialModifier(GeneralAttribute.WILLPOWER, -10);

        DWARF.setRacialModifier(GeneralAttribute.PERCEPTION, 20);
        DWARF.setRacialModifier(GeneralAttribute.STRENGTH, 10);
        DWARF.setRacialModifier(GeneralAttribute.VITALITY, 10);
        DWARF.setRacialModifier(GeneralAttribute.SWIFTNESS, -20);
        DWARF.setRacialModifier(GeneralAttribute.DEXTERITY, -10);
        DWARF.setRacialModifier(GeneralAttribute.CHARISMA, -10);
        DWARF.setRacialModifier(GeneralAttribute.BEAUTY, -10);

        ELF.setRacialModifier(GeneralAttribute.INTELLIGENCE, 20);
        ELF.setRacialModifier(GeneralAttribute.WILLPOWER, 10);
        ELF.setRacialModifier(GeneralAttribute.BEAUTY, 10);
        ELF.setRacialModifier(GeneralAttribute.STRENGTH, -20);
        ELF.setRacialModifier(GeneralAttribute.ENDURANCE, -20);
        ELF.setRacialModifier(GeneralAttribute.VITALITY, -10);

        DARK_ELF.setRacialModifier(GeneralAttribute.DEXTERITY, 20);
        DARK_ELF.setRacialModifier(GeneralAttribute.SWIFTNESS, 10);
        DARK_ELF.setRacialModifier(GeneralAttribute.PERCEPTION, 10);
        DARK_ELF.setRacialModifier(GeneralAttribute.STRENGTH, -20);
        DARK_ELF.setRacialModifier(GeneralAttribute.WISDOM, -20);
        DARK_ELF.setRacialModifier(GeneralAttribute.CHARISMA, -10);

        LIZARDMEN.setRacialModifier(GeneralAttribute.DEXTERITY, 30);
        LIZARDMEN.setRacialModifier(GeneralAttribute.PERCEPTION, 10);
        LIZARDMEN.setRacialModifier(GeneralAttribute.STRENGTH, -30);
        LIZARDMEN.setRacialModifier(GeneralAttribute.INTELLIGENCE, -20);
        LIZARDMEN.setRacialModifier(GeneralAttribute.VITALITY, -10);

        GNOME.setRacialModifier(GeneralAttribute.INTELLIGENCE, 30);
        GNOME.setRacialModifier(GeneralAttribute.WILLPOWER, 10);
        GNOME.setRacialModifier(GeneralAttribute.STRENGTH, -30);
        GNOME.setRacialModifier(GeneralAttribute.VITALITY, -20);
        GNOME.setRacialModifier(GeneralAttribute.DEXTERITY, -10);

        DRACONIC.setRacialModifier(GeneralAttribute.WILLPOWER, 30);
        DRACONIC.setRacialModifier(GeneralAttribute.ENDURANCE, 10);
        DRACONIC.setRacialModifier(GeneralAttribute.DEXTERITY, -30);
        DRACONIC.setRacialModifier(GeneralAttribute.SWIFTNESS, -20);
        DRACONIC.setRacialModifier(GeneralAttribute.PERCEPTION, -10);
    }

    public static final int NO_RACIAL_MODIFIER = 0;

    private final LinkedHashMap<Attribute, Integer> racialAttributeModifiers = new LinkedHashMap<>();

    private void setRacialModifier(Attribute attribute, int value) {
        racialAttributeModifiers.put(attribute, value);
    }

    public int getRacialModifier(Attribute attribute) {
        return racialAttributeModifiers.containsKey(attribute) ? racialAttributeModifiers.get(attribute) : NO_RACIAL_MODIFIER;
    }

    public String getName() {
        return this.name();
    }

    public Map<Attribute, Integer> getRacialAttributeModifiers() {
        return racialAttributeModifiers;
    }
}
