package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.type.SkillAttribute;

public class SkillAttributeData extends AttributeData {

    private final int actualXp;
    private final int nextLevelXp;

    private SkillAttributeData(Attribute attribute, int actual, int maximum, AttributeModifierData[] attributeModifierDataArray, int actualXp, int nextLevelXp) {
        super(attribute, actual, maximum, attributeModifierDataArray);

        this.actualXp = actualXp;
        this.nextLevelXp = nextLevelXp;
    }

    public int getActualXp() {
        return actualXp;
    }

    public int getNextLevelXp() {
        return nextLevelXp;
    }

    public static class SkillAttributeDataBuilder extends AttributeDataBuilder {

        private int actualXp;
        private int nextLevelXp;

        public SkillAttributeDataBuilder(SkillAttribute attribute) {
            super(attribute);
        }

        public int getActualXp() {
            return actualXp;
        }

        public void setActualXp(int actualXp) {
            this.actualXp = actualXp;
        }

        public int getNextLevelXp() {
            return nextLevelXp;
        }

        public void setNextLevelXp(int nextLevelXp) {
            this.nextLevelXp = nextLevelXp;
        }

        @Override
        public SkillAttributeData build() {
            return new SkillAttributeData(attribute, actual, maximum, attributeModifierArray, actualXp, nextLevelXp);
        }
    }
}
