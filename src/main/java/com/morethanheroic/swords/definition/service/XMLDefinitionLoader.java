package com.morethanheroic.swords.definition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLDefinitionLoader {

    private static final SchemaFactory schemaFactory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    @Autowired
    private ApplicationContext applicationContext;

    public List loadDefinitions(Class clazz, String resourcePath, String schemaPath) throws JAXBException, IOException, SAXException {
        return unmarshallTargetFiles(buildUnmarshaller(clazz, schemaPath), getTargetFiles(resourcePath));
    }

    private ArrayList unmarshallTargetFiles(Unmarshaller unmarshaller, File[] files) throws JAXBException {
        ArrayList list = new ArrayList<>();

        for (File file : files) {
            list.add(unmarshaller.unmarshal(file));
        }

        return list;
    }

    private File[] getTargetFiles(String resourcePath) throws IOException {
        return applicationContext.getResource(resourcePath).getFile().listFiles();
    }

    private Unmarshaller buildUnmarshaller(Class clazz, String schemaPath) throws IOException, SAXException, JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
        unmarshaller.setSchema(buildSchema(schemaPath));

        return unmarshaller;
    }

    private Schema buildSchema(String schemaPath) throws IOException, SAXException {
        return schemaFactory.newSchema(new StreamSource(applicationContext.getResource(schemaPath).getInputStream()));
    }
}
