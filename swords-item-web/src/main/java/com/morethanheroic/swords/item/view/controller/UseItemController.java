package com.morethanheroic.swords.item.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.service.item.UseItemService;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UseItemController {

    private final ResponseFactory responseFactory;
    private final UseItemService useItemService;
    private final ItemDefinitionCache itemDefinitionCache;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @GetMapping("/item/use/{itemId}")
    @SuppressWarnings("unchecked")
    public Response useItem(UserEntity userEntity, SessionEntity sessionEntity, @RequestParam Map<String, String> allRequestParams, @PathVariable int itemId) {
        if (useItemService.canUseItem(userEntity, itemDefinitionCache.getDefinition(itemId))) {
            useItemService.useItem(userEntity, itemDefinitionCache.getDefinition(itemId), new CombatEffectDataHolder((Map) allRequestParams, sessionEntity));

            return responseFactory.successfulResponse(userEntity);
        }

        return responseFactory.failedResponse(userEntity);
    }
}
