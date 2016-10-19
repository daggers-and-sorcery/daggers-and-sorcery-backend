package com.morethanheroic.swords.admin.view.controller.item;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Profile("admin")
public class ItemListController {

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @RequestMapping(path = "/admin/item/list", method = RequestMethod.GET)
    public List<ItemDefinition> listItems() {
        return itemDefinitionCache.getDefinitions();
    }
}
