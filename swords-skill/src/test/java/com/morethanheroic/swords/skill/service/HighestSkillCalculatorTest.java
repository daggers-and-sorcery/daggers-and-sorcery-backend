package com.morethanheroic.swords.skill.service;

import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HighestSkillCalculatorTest {

    @Mock
    private SkillEntity skillEntity;

    private HighestSkillCalculator underTest = new HighestSkillCalculator();

    @Test
    public void testWhenHighestSkillCalculatorCalledWithCorrectSkillEntity() {
        when(skillEntity.getSkillXp(SkillType.CROSSBOWS)).thenReturn(98);
        when(skillEntity.getSkillXp(SkillType.DAGGERS)).thenReturn(97);
        when(skillEntity.getSkillXp(SkillType.ARMORLESS_DEFENSE)).thenReturn(100);
        when(skillEntity.getSkillXp(SkillType.LIGHT_ARMOR)).thenReturn(96);
        when(skillEntity.getSkillXp(SkillType.LONGBOWS)).thenReturn(95);
        when(skillEntity.getSkillXp(SkillType.COOKING)).thenReturn(99);
        when(skillEntity.getSkillXp(SkillType.FISTFIGHT)).thenReturn(94);

        final List<SkillType> result = underTest.getHighestSkills(skillEntity);

        assertThat(result.contains(SkillType.ARMORLESS_DEFENSE), is(true));
        assertThat(result.contains(SkillType.COOKING), is(true));
        assertThat(result.contains(SkillType.CROSSBOWS), is(true));
        assertThat(result.size(), is(3));
    }
}
