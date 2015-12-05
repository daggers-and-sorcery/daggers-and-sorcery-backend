package com.morethanheroic.swords.definition.service.loader;

import com.morethanheroic.swords.definition.service.loader.unmarshaller.UnmarshallerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Load xml classes and validate them based on the given schema url.
 */
@Service
public class XmlDefinitionLoader {

    private static final SchemaFactory SCHEMA_FACTORY = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    private static final int MAXIMUM_ENTRIES_READ = 100;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UnmarshallerBuilder unmarshallerBuilder;

    public List loadDefinitions(Class clazz, String resourcePath, String schemaPath) throws IOException {
        try {
            return unmarshallTargetFiles(unmarshallerBuilder.buildUnmarshaller(clazz, schemaPath), resourcePath);
        } catch (SAXException | JAXBException e) {
            throw new IOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private List unmarshallTargetFiles(Unmarshaller unmarshaller, String resourcePath) throws JAXBException, IOException {
        final List list = new ArrayList<>();

        for (int i = 1; i < MAXIMUM_ENTRIES_READ; i++) {
            final Resource resource = applicationContext.getResource(resourcePath + i + ".xml");

            if (!resource.exists()) {
                return list;
            }

            list.add(unmarshaller.unmarshal(applicationContext.getResource(resourcePath + i + ".xml").getInputStream()));
        }

        throw new IllegalStateException("Should be here! There is more items to read than the actual maxvalue!");
    }
}
