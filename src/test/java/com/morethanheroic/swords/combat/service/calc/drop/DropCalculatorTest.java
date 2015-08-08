package com.morethanheroic.swords.combat.service.calc.drop;

import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.monster.service.domain.DropDefinition;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DropCalculatorTest {

    @Test
    public void testCalculateDrop() throws Exception {
        MonsterDefinition monster = new MonsterDefinition() {
            public List<DropDefinition> getDropDefinitions() {
                List<DropDefinition> drops = new LinkedList<>();

                drops.add(new DropDefinition(1, 10, 100));
                drops.add(new DropDefinition(2, 3, 100));
                drops.add(new DropDefinition(3, 8, 0));

                return drops;
            }
        };

        DropCalculator dropCalculator = new DropCalculator();

        ArrayList<Drop> drops = dropCalculator.calculateDrop(monster);

        Assert.assertEquals(drops.get(0).getItem(), 1);
        Assert.assertEquals(drops.get(0).getAmount(), 10);
        Assert.assertEquals(drops.get(1).getItem(), 2);
        Assert.assertEquals(drops.get(1).getAmount(), 3);
    }
}