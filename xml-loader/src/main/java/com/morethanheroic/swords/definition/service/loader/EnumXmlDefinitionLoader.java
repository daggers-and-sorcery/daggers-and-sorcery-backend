package com.morethanheroic.swords.definition.service.loader;

import com.morethanheroic.swords.definition.service.loader.domain.DefinitionLoadingContext;
import com.morethanheroic.swords.definition.service.loader.domain.EnumDefinitionLoadingContext;
import com.morethanheroic.swords.definition.service.loader.exception.DefinitionLoaderException;
import com.morethanheroic.swords.definition.service.loader.unmarshaller.UnmarshallerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * An {@link XmlDefinitionLoader} that loads xml information based on a {@link java.lang.Enum}. The loader looks
 * for files in the given resource path with the enum names lowercase and _ replaces with -.
 * For example:
 * DWARF -> resourcePath/dwarf.xml
 * DARK_ELF -> resourcePath/dark-elf.xml
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EnumXmlDefinitionLoader implements XmlDefinitionLoader<Class<? extends Enum>> {

    private final UnmarshallerBuilder unmarshallerBuilder;
    private final ApplicationContext applicationContext;

    @Override
    public <T> List<T> loadDefinitions(final DefinitionLoadingContext definitionLoadingContext) {
        final EnumDefinitionLoadingContext enumDefinitionLoadingContext = (EnumDefinitionLoadingContext) definitionLoadingContext;

        try {
            return unmarshallTargetFiles(unmarshallerBuilder.buildUnmarshaller(enumDefinitionLoadingContext.getClazz(), enumDefinitionLoadingContext.getSchemaPath()), enumDefinitionLoadingContext.getResourcePath(), enumDefinitionLoadingContext.getTarget());
        } catch (JAXBException | IOException e) {
            throw new DefinitionLoaderException("Error while loading enum based xml definitions.", e);
        }
    }

    @SuppressWarnings("unchecked")
    private List unmarshallTargetFiles(final Unmarshaller unmarshaller,final String resourcePath,final Class<? extends Enum> enumClass) throws JAXBException,
            IOException {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(actualEnum -> unmarshallTargetFile(unmarshaller, resourcePath, actualEnum))
                .collect(Collectors.toList());
    }

    private Object unmarshallTargetFile(final Unmarshaller unmarshaller, final String resourcePath, final Enum actualEnum) {
        final String enumResourcePath = resourcePath + getFileNameFromEnum(actualEnum);
        final Resource resource = applicationContext.getResource(enumResourcePath);

        if (!resource.exists()) {
            throw new DefinitionLoaderException("Definition for enum: " + actualEnum.name() + " at " + enumResourcePath + " does not exists!");
        }

        try {
            return unmarshaller.unmarshal(applicationContext.getResource(enumResourcePath).getInputStream());
        } catch (JAXBException | IOException e) {
            throw new DefinitionLoaderException("Error occured while loading definitions!", e);
        }
    }

    private String getFileNameFromEnum(Enum enumValue) {
        return enumValue.name().toLowerCase(Locale.ENGLISH).replace('_', '-') + ".xml";
    }
}
