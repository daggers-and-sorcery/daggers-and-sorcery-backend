package com.morethanheroic.swords.admin.view.controller.item;

import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.loot.domain.DropDefinition;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.ScavengingDefinition;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Profile("admin")
@RequiredArgsConstructor
public class ItemController {

    private final ItemDefinitionCache itemDefinitionCache;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @RequestMapping(path = "/admin/item/{itemId}", method = RequestMethod.GET)
    public Map<String, Object> getItem(@PathVariable int itemId) {

        final Map<String, Object> result = new HashMap<>();

        result.put("item", itemDefinitionCache.getDefinition(itemId));
        result.put("drop", calculateDropData(itemId));

        return result;
    }

    private List<Map<String, Object>> calculateDropData(int itemId) {
        final List<Map<String, Object>> monsters = new ArrayList<>();

        for (MonsterDefinition monsterDefinition : monsterDefinitionCache.getDefinitions()) {
            for (DropDefinition dropDefinition : monsterDefinition.getDropDefinitions()) {
                if (dropDefinition.getItem().getId() == itemId) {
                    final Map<String, Object> dropdata = new HashMap<>();

                    dropdata.put("monster", monsterDefinition);
                    dropdata.put("drop", dropDefinition);
                    dropdata.put("scavenge", false);

                    monsters.add(dropdata);

                    break;
                }
            }

            for (ScavengingDefinition scavengingDefinition : monsterDefinition.getScavengingDefinitions()) {
                if (scavengingDefinition.getItem().getId() == itemId) {
                    final Map<String, Object> dropdata = new HashMap<>();

                    dropdata.put("monster", monsterDefinition);
                    dropdata.put("drop", scavengingDefinition);
                    dropdata.put("scavenge", true);

                    monsters.add(dropdata);

                    break;
                }
            }
        }

        return monsters;
    }
}
