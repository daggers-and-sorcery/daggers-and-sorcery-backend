package com.morethanheroic.swords.configuration.service.definition.domain;

import com.morethanheroic.swords.configuration.domain.Configuration;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConfigurationDefinition {

    private Configuration id;
    private int value;
}
