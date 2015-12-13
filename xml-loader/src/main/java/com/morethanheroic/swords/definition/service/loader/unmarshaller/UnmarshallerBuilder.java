package com.morethanheroic.swords.definition.service.loader.unmarshaller;

        import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.ApplicationContext;
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

/**
 * Build an {@link Unmarshaller} that can be used in {@link NumericXmlDefinitionLoader}.
 */
@Service
public class UnmarshallerBuilder {

    private static final SchemaFactory SCHEMA_FACTORY = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    @Autowired
    private ApplicationContext applicationContext;

    public Unmarshaller buildUnmarshaller(Class clazz, String schemaPath) throws IOException {
        try {
            final Unmarshaller unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();

            unmarshaller.setSchema(buildSchema(schemaPath));

            return unmarshaller;
        } catch (SAXException | JAXBException e) {
            throw new IOException(e);
        }
    }

    private Schema buildSchema(String schemaPath) throws IOException, SAXException {
        return SCHEMA_FACTORY.newSchema(new StreamSource(applicationContext.getResource(schemaPath).getInputStream()));
    }
}
