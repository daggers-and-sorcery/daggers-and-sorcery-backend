package com.morethanheroic.swords.event.service.event.impl;

import com.morethanheroic.swords.event.domain.EventType;
import com.morethanheroic.swords.event.service.event.Event;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoxSkinCuredEvent implements Event {

    @Autowired
    private InventoryFacade inventoryFacade;

    @Override
    public void processEvent(UserEntity userEntity) {
        //TODO: add correct item and xp
        inventoryFacade.getInventory(userEntity).addItem(20, 20);
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public EventType getType() {
        return EventType.LEATHERWORKING_CURING;
    }

    @Override
    public String getName() {
        return "Fox skin (cured)";
    }

    @Override
    public int getLength() {
        return 3 * 60 * 1000;
    }
}
