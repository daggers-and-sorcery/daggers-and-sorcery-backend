package com.morethanheroic.swords.item.view.controller;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.item.service.response.UseItemResponseBuilder;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
    @SuppressWarnings("unchecked")
    public Response useItem(UserEntity userEntity, HttpSession httpSession, @RequestParam Map<String, String> allRequestParams, @PathVariable int itemId) {
        if (useItemService.canUseItem(userEntity, itemDefinitionCache.getItemDefinition(itemId))) {
            useItemService.useItem(userEntity, itemDefinitionCache.getItemDefinition(itemId), new CombatEffectDataHolder((Map) allRequestParams, httpSession));

            return useItemResponseBuilder.build(userEntity, true);
        }

        return useItemResponseBuilder.build(userEntity, false);
    }
}
