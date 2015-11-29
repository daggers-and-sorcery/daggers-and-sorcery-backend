package com.morethanheroic.swords.shop.service.loader;

import com.morethanheroic.swords.definition.service.XmlDefinitionLoader;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.loader.domain.RawShopDefinition;
import com.morethanheroic.swords.shop.service.transformer.ShopDefinitionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopDefinitionLoader {

    @Autowired
    private XmlDefinitionLoader xmlDefinitionLoader;

    @Autowired
    private ShopDefinitionTransformer shopDefinitionTransformer;

    public List<ShopDefinition> loadShopDefinitions() throws IOException {
        return loadRawShopDefinitions().stream().map(shopDefinitionTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<RawShopDefinition> loadRawShopDefinitions() throws IOException {
        return xmlDefinitionLoader.loadDefinitions(RawShopDefinition.class, "classpath:data/shop/definition/", "classpath:data/shop/schema.xsd");
    }
}
