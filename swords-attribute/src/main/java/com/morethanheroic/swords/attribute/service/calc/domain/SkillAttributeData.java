package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;

import java.util.List;

public class SkillAttributeData extends AttributeData {

    private final long actualXp;
    private final long nextLevelXp;
    private final long xpBetweenLevels;

    private SkillAttributeData(Attribute attribute, AttributeCalculationResult actual, AttributeCalculationResult maximum, List<AttributeModifierEntry> attributeModifierEntryArray, long actualXp, long nextLevelXp, long xpBetweenLevels) {
        super(attribute, actual, maximum, attributeModifierEntryArray);

        this.actualXp = actualXp;
        this.nextLevelXp = nextLevelXp;
        this.xpBetweenLevels = xpBetweenLevels;
    }

    public long getActualXp() {
        return actualXp;
    }

    public long getNextLevelXp() {
        return nextLevelXp;
    }

    public long getXpBetweenLevels() {
        return xpBetweenLevels;
    }

    public static class SkillAttributeDataBuilder {

        private long actualXp;
        private long nextLevelXp;
        private long xpBetweenLevels;

        protected Attribute attribute;
        protected AttributeCalculationResult maximum;
        protected AttributeCalculationResult actual;
        protected List<AttributeModifierEntry> attributeModifierArray;

        public SkillAttributeDataBuilder(SkillAttribute attribute) {
            this.attribute = attribute;
        }

        public void setMaximum(AttributeCalculationResult maximum) {
            this.maximum = maximum;
        }

        public void setActual(AttributeCalculationResult actual) {
            this.actual = actual;
        }

        public void setAttributeModifierData(List<AttributeModifierEntry> attributeModifierArray) {
            this.attributeModifierArray = attributeModifierArray;
        }

        public long getActualXp() {
            return actualXp;
        }

        public void setActualXp(long actualXp) {
            this.actualXp = actualXp;
        }

        public long getNextLevelXp() {
            return nextLevelXp;
        }

        public void setNextLevelXp(long nextLevelXp) {
            this.nextLevelXp = nextLevelXp;
        }

        public SkillAttributeData build() {
            return new SkillAttributeData(attribute, actual, maximum, attributeModifierArray, actualXp, nextLevelXp, xpBetweenLevels);
        }

        public long getXpBetweenLevels() {
            return xpBetweenLevels;
        }

        public void setXpBetweenLevels(long xpBetweenLevels) {
            this.xpBetweenLevels = xpBetweenLevels;
        }
    }
}
