package com.morethanheroic.swords.item.service.loader;

import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import com.morethanheroic.swords.item.service.transformer.ItemDefinitionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemDefinitionLoader {

    @Autowired
    private XMLDefinitionLoader xmlDefinitionLoader;

    @Autowired
    private ItemDefinitionTransformer itemDefinitionTransformer;

    public List<ItemDefinition> loadItemDefinitions() throws Exception {
        List<RawItemDefinition> rawItemDefinitions = loadRawItemDefinitions();
        List<ItemDefinition> result = new ArrayList<>();

        for (RawItemDefinition rawItemDefinition : rawItemDefinitions) {
            result.add(itemDefinitionTransformer.transform(rawItemDefinition));
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private List<RawItemDefinition> loadRawItemDefinitions() throws JAXBException, IOException, SAXException {
        return xmlDefinitionLoader.loadDefinitions(RawItemDefinition.class, "classpath:data/item/definition/", "classpath:data/item/schema.xsd");
    }
}
