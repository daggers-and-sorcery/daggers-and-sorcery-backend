package com.morethanheroic.swords.shop.view.request.advice;

import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.definition.cache.ShopDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
@RequiredArgsConstructor
public class ShopDefinitionControllerAdvice {

    private final ShopDefinitionCache shopDefinitionCache;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(ShopDefinition.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(final String rawShopId) {
                final int shopId = convertToShopId(rawShopId);

                if (!shopDefinitionCache.isDefinitionExists(shopId)) {
                    throw new NotFoundException("Shop definition doesn't exist for id: " + shopId);
                }

                setValue(shopDefinitionCache.getDefinition(shopId));
            }
        });
    }

    private int convertToShopId(final String rawShopId) {
        if (!StringUtils.isNumeric(rawShopId)) {
            throw new IllegalArgumentException("The provided shop id must be numeric!");
        }

        return Integer.parseInt(rawShopId);
    }
}
