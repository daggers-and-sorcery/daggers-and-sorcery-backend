package com.morethanheroic.swords.shop.service.definition.cache;

import com.morethanheroic.definition.cache.DefinitionCache;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.definition.loader.ShopDefinitionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class ShopDefinitionCache implements DefinitionCache<Integer, ShopDefinition> {

    @Autowired
    private ShopDefinitionLoader shopDefinitionLoader;

    private Map<Integer, ShopDefinition> shopDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        final List<ShopDefinition> rawShopDefinition = shopDefinitionLoader.loadDefinitions();

        for (ShopDefinition shopDefinition : rawShopDefinition) {
            shopDefinitionMap.put(shopDefinition.getId(), shopDefinition);
        }
    }

    @Override
    public ShopDefinition getDefinition(Integer shopId) {
        return shopDefinitionMap.get(shopId);
    }

    @Override
    public int getSize() {
        return shopDefinitionMap.size();
    }

    @Override
    public List<ShopDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(shopDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(Integer shopId) {
        return shopDefinitionMap.containsKey(shopId);
    }
}
