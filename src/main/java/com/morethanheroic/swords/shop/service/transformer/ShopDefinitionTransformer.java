package com.morethanheroic.swords.shop.service.transformer;

import com.morethanheroic.swords.shop.service.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.loader.domain.RawShopDefinition;
import org.springframework.stereotype.Service;

@Service
public class ShopDefinitionTransformer {

    public ShopDefinition transform(RawShopDefinition rawShopDefinition) {
        return ShopDefinition.builder()
                .id(rawShopDefinition.getId())
                .name(rawShopDefinition.getName())
                .build();
    }
}
