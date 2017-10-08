package com.morethanheroic.swords.shop.service.definition.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.definition.loader.domain.RawShopDefinition;
import com.morethanheroic.swords.shop.service.definition.transformer.ShopDefinitionTransformer;
import com.morethanheroic.xml.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.NumericDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ShopDefinitionLoader implements DefinitionLoader<ShopDefinition> {

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final ShopDefinitionTransformer shopDefinitionTransformer;

    @Override
    public List<ShopDefinition> loadDefinitions() {
        return loadRawShopDefinitions().stream()
                .map(shopDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawShopDefinition> loadRawShopDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawShopDefinition>builder()
                        .clazz(RawShopDefinition.class)
                        .resourcePath("classpath:data/shop/definition/")
                        .schemaPath("classpath:data/shop/schema.xsd")
                        .build()
        );
    }
}
