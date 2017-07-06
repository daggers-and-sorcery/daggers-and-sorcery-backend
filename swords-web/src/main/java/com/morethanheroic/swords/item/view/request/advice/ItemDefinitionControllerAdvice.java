package com.morethanheroic.swords.item.view.request.advice;

import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;

@ControllerAdvice
@RequiredArgsConstructor
public class ItemDefinitionControllerAdvice {

    private final ItemDefinitionCache itemDefinitionCache;
    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;
    private final HttpServletRequest request;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(ItemDefinition.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(final String rawItemId) {
                int itemId = convertToItemId(rawItemId);

                final IdentificationType identificationType = unidentifiedItemIdCalculator.isIdentified(itemId);

                if (identificationType == IdentificationType.UNIDENTIFIED) {
                    //TODO: Use a factory for SessionEntity if it's exists!
                    itemId = unidentifiedItemIdCalculator.getRealItemId(new SessionEntity(request.getSession()), itemId);
                }

                if (!itemDefinitionCache.isDefinitionExists(itemId)) {
                    throw new NotFoundException();
                }

                setValue(itemDefinitionCache.getDefinition(itemId));
            }
        });
    }

    private int convertToItemId(final String rawItemId) {
        if (!StringUtils.isNumeric(rawItemId)) {
            throw new IllegalArgumentException("The provided item id must be numeric!");
        }

        return Integer.parseInt(rawItemId);
    }
}