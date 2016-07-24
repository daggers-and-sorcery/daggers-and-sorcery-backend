package com.morethanheroic.swords.admin.view.controller;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("development")
public class ItemController {

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @RequestMapping(path = "/admin/item/{id}", method = RequestMethod.GET)
    public ItemDefinition listItems(@PathVariable int id) {
        return itemDefinitionCache.getDefinition(id);
    }
}
