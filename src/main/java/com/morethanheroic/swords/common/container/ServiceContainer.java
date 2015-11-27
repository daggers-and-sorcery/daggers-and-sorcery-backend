package com.morethanheroic.swords.common.container;

import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.map.service.MapInfoDefinitionManager;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.skill.service.SkillManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ServiceContainer {

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private MapManager mapManager;

    @Autowired
    private SkillManager skillManager;
}
