package com.morethanheroic.swords.item.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.service.item.UseItemService;
import com.morethanheroic.swords.combat.service.item.domain.ItemUsageContext;
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

/**
 * This controller is responsible for all of the use item functionality.
 */
@RestController
@RequiredArgsConstructor
public class UseItemController {

    private final ResponseFactory responseFactory;
    private final UseItemService useItemService;
    private final ItemDefinitionCache itemDefinitionCache;

    /**
     * An endpoint that provides a way to the user to use an item.
     *
     * @param userEntity       the user that is using the item
     * @param sessionEntity    the session of the user
     * @param allRequestParams additional parameters with the usage
     * @param itemId           the id of the used item
     * @return successful or failed response
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @GetMapping("/item/use/{itemId}")
    public Response useItem(final UserEntity userEntity, final SessionEntity sessionEntity,
            final @RequestParam Map<String, String> allRequestParams, @PathVariable final int itemId) {
        if (useItemService.canUseItem(userEntity, itemDefinitionCache.getDefinition(itemId))) {
            useItemService.useItem(userEntity, itemDefinitionCache.getDefinition(itemId),
                    ItemUsageContext.builder()
                            .parameters(allRequestParams)
                            .sessionEntity(sessionEntity)
                            .build()
            );

            return responseFactory.successfulResponse(userEntity);
        }

        return responseFactory.failedResponse(userEntity);
    }
}
