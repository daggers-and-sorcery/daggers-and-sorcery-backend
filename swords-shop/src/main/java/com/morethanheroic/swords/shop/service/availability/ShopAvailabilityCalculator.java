package com.morethanheroic.swords.shop.service.availability;

import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Calculate if the shop is accessible for a given user.
 */
@Service
@RequiredArgsConstructor
public class ShopAvailabilityCalculator {

    private final List<ShopAvailabilityEvaluator> shopAvailabilityEvaluators;

    //TODO: Write an availability calculator to check that the user is in the same location as the shop.
    public boolean isAvailable(final UserEntity userEntity, final ShopDefinition shopDefinition) {
        return shopAvailabilityEvaluators.stream()
                .allMatch(shopAvailabilityEvaluators -> shopAvailabilityEvaluators.isAvailable(userEntity, shopDefinition));
    }
}
