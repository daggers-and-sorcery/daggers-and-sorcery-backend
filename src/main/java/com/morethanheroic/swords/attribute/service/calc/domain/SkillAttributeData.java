package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierData;

public class SkillAttributeData extends AttributeData {

    private final long actualXp;
    private final long nextLevelXp;
    private final long xpBetweenLevels;

    private SkillAttributeData(Attribute attribute, AttributeCalculationResult actual, int maximum, AttributeModifierData[] attributeModifierDataArray, long actualXp, long nextLevelXp, long xpBetweenLevels) {
        super(attribute, actual, maximum, attributeModifierDataArray);

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

    public static class SkillAttributeDataBuilder extends AttributeDataBuilder {

        private long actualXp;
        private long nextLevelXp;
        private long xpBetweenLevels;

        public SkillAttributeDataBuilder(SkillAttribute attribute) {
            super(attribute);
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

        @Override
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
