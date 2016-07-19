package com.morethanheroic.swords.definition.service.loader;

import com.morethanheroic.swords.definition.service.loader.exception.DefinitionLoaderException;
import com.morethanheroic.swords.definition.service.loader.unmarshaller.UnmarshallerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Load xml classes and validate them based on the given schema url. it tries to load that many files with numeric numbers
 * as given in the maximumFileCount in the resourcePath folder. (1.xml, 2.xml, 3.xml etc...) If the loader found more
 * files that given in the maximumFileCount then throws an {@link com.sun.javaws.exceptions.InvalidArgumentException} otherwise
 * loads as many files as it founds.
 */
@Service
public class NumericXmlDefinitionLoader implements XmlDefinitionLoader<Integer> {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UnmarshallerBuilder unmarshallerBuilder;

    public List loadDefinitions(Class clazz, String resourcePath, String schemaPath, Integer maximumFileCount) throws IOException {
        try {
            return unmarshallTargetFiles(unmarshallerBuilder.buildUnmarshaller(clazz, schemaPath), resourcePath, maximumFileCount);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private List unmarshallTargetFiles(Unmarshaller unmarshaller, String resourcePath, int maximumFileCount) throws JAXBException, IOException {
        final List list = new ArrayList<>();

        for (int i = 1; i < maximumFileCount; i++) {
            try {
                final Resource resource = applicationContext.getResource(resourcePath + i + ".xml");

                if (!resource.exists()) {
                    return list;
                }

                list.add(unmarshaller.unmarshal(applicationContext.getResource(resourcePath + i + ".xml").getInputStream()));
            } catch (Exception e) {
                throw new DefinitionLoaderException("Error happened while trying to load definition: " + resourcePath + " with id: " + i, e);
            }
        }

        throw new IllegalStateException("Should be here! There is more items to read than the actual maxvalue!");
    }
}
