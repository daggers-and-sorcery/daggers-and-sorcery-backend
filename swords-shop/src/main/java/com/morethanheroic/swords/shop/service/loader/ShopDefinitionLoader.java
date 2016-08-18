package com.morethanheroic.swords.shop.service.loader;

import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.loader.domain.RawShopDefinition;
import com.morethanheroic.swords.shop.service.transformer.ShopDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopDefinitionLoader implements DefinitionLoader<ShopDefinition> {

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final ShopDefinitionTransformer shopDefinitionTransformer;

    @Override
    public List<ShopDefinition> loadDefinitions() throws IOException {
        return loadRawShopDefinitions().stream().map(shopDefinitionTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<RawShopDefinition> loadRawShopDefinitions() throws IOException {
        return numericXmlDefinitionLoader.loadDefinitions(RawShopDefinition.class, "classpath:data/shop/definition/", "classpath:data/shop/schema.xsd", 100);
    }
}
