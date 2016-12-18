package com.morethanheroic.swords.definition.service.loader;

import com.morethanheroic.swords.definition.service.loader.exception.DefinitionLoaderException;
import com.morethanheroic.swords.definition.service.loader.unmarshaller.UnmarshallerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Load xml classes and validate them based on the given schema url. it tries to load that many files with numeric numbers
 * as given in the maximumFileCount in the resourcePath folder. (1.xml, 2.xml, 3.xml etc...) If the loader found more
 * files that given in the maximumFileCount then throws an {@link com.sun.javaws.exceptions.InvalidArgumentException} otherwise
 * loads as many files as it founds.
 */
@Service
@RequiredArgsConstructor
public class NumericXmlDefinitionLoader implements XmlDefinitionLoader<Integer> {

    private final UnmarshallerBuilder unmarshallerBuilder;

    //TODO: maximumFileCount is not needed anymore, remove it somehow!
    public <T> List<T> loadDefinitions(Class<T> clazz, String resourcePath, String schemaPath, Integer maximumFileCount) throws IOException {
        try {
            return unmarshallTargetFiles(unmarshallerBuilder.buildUnmarshaller(clazz, schemaPath), resourcePath);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    @SuppressWarnings({"unchecked", "checkstyle:IllegalCatch"})
    private List unmarshallTargetFiles(Unmarshaller unmarshaller, String resourcePath) throws JAXBException, IOException {
        final ResourcePatternResolver resourcePatternResolver = buildResourcePatternResolver();

        return Arrays.stream(resourcePatternResolver.getResources(resourcePath + "*.xml"))
                .map(resource -> {
                    try {
                        return unmarshaller.unmarshal(resource.getInputStream());
                    } catch (JAXBException | IOException e) {
                        throw new DefinitionLoaderException("Error happened while trying to load definition: "
                                + resourcePath + " with name: " + resource.getFilename(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    private ResourcePatternResolver buildResourcePatternResolver() {
        return new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
    }
}
