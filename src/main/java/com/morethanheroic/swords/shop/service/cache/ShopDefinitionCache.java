package com.morethanheroic.swords.shop.service.cache;

import com.morethanheroic.swords.shop.service.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.loader.ShopDefinitionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopDefinitionCache {

    @Autowired
    private ShopDefinitionLoader shopDefinitionLoader;

    private Map<Integer, ShopDefinition> shopDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        List<ShopDefinition> rawShopDefinition = shopDefinitionLoader.loadShopDefinitions();

        for (ShopDefinition shopDefinition : rawShopDefinition) {
            shopDefinitionMap.put(shopDefinition.getId(), shopDefinition);
        }
    }

    public ShopDefinition getShopDefinition(int shopId) {
        return shopDefinitionMap.get(shopId);
    }

    public boolean isShopExists(int shopId) {
        return shopDefinitionMap.containsKey(shopId);
    }
}
