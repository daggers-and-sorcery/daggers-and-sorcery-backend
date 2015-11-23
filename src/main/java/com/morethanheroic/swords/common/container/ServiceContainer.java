package com.morethanheroic.swords.common.container;

import com.morethanheroic.swords.inventory.service.InventoryFacade;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ServiceContainer {

    @Autowired
    private InventoryFacade inventoryFacade;
}
