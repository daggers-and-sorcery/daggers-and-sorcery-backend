package com.morethanheroic.swords.shop.service.price.modifier.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

/**
 * An easy to extend context for price modification.
 */
@Getter
@Builder
public class ItemPriceModificationContext {

    private final UserEntity userEntity;
}
