package com.morethanheroic.swords.item.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.item.service.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.UseItemResponseBuilder;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseItemController {

    private final UseItemService useItemService;
    private final ItemDefinitionCache itemDefinitionCache;
    private final UseItemResponseBuilder useItemResponseBuilder;

    @Autowired
    public UseItemController(UseItemService useItemService, ItemDefinitionCache itemDefinitionCache, UseItemResponseBuilder useItemResponseBuilder) {
        this.useItemService = useItemService;
        this.itemDefinitionCache = itemDefinitionCache;
        this.useItemResponseBuilder = useItemResponseBuilder;
    }

    @Transactional
    @RequestMapping(value = "/item/use/{itemId}", method = RequestMethod.GET)
    public Response useItem(UserEntity userEntity, @PathVariable int itemId) {
        if (useItemService.canUseItem(userEntity, itemDefinitionCache.getItemDefinition(itemId))) {
            useItemService.useItem(userEntity, itemDefinitionCache.getItemDefinition(itemId));

            return useItemResponseBuilder.build(userEntity, true);
        }

        return useItemResponseBuilder.build(userEntity, false);
    }
}
