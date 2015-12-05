package com.morethanheroic.swords.definition.service.loader;

import com.morethanheroic.swords.definition.service.loader.unmarshaller.UnmarshallerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link XmlDefinitionLoader} that loads xml information based on a {@link java.lang.Enum}. The loader looks
 * for files in the given resource path with the enum names lowercase and _ replaces with -.
 *
 * For example:
 * DWARF -> resourcePath/dwarf.xml
 * DARK_ELF -> resourcePath/dark-elf.xml
 */
@Service
@Slf4j
public class EnumXmlDefinitionLoader implements XmlDefinitionLoader<Class<? extends Enum>> {

    @Autowired
    private UnmarshallerBuilder unmarshallerBuilder;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public List loadDefinitions(Class clazz, String resourcePath, String schemaPath, Class<? extends Enum> target) throws IOException {
        try {
            return unmarshallTargetFiles(unmarshallerBuilder.buildUnmarshaller(clazz, schemaPath), resourcePath, target);
        } catch (SAXException | JAXBException e) {
            throw new IOException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private List unmarshallTargetFiles(Unmarshaller unmarshaller, String resourcePath, Class<? extends Enum> enumClass) throws JAXBException, IOException {
        final List list = new ArrayList<>();

        for (Enum actualEnum : enumClass.getEnumConstants()) {
            final String enumFileName = resourcePath + getFileNameFromEnum(actualEnum);
            final Resource resource = applicationContext.getResource(enumFileName);

            if (!resource.exists()) {
                throw new IllegalArgumentException("Definition for enum: " + actualEnum.name() + " does not exists!");
            }

            list.add(unmarshaller.unmarshal(applicationContext.getResource(enumFileName).getInputStream()));
        }

        log.info("Loaded " + list.size() + " race entity definitions.");

        return list;
    }

    private String getFileNameFromEnum(Enum enumValue) {
        return enumValue.name().toLowerCase().replace('_', '-') + ".xml";
    }
}
