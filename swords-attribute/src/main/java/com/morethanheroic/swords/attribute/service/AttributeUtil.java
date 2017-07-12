package com.morethanheroic.swords.attribute.service;

import com.google.common.collect.ImmutableSet;
import com.morethanheroic.swords.attribute.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
public class AttributeUtil {

    private static final Set<Attribute> ALL_ATTRIBUTES_SET = ImmutableSet.<Attribute>builder()
            .addAll(Arrays.asList(BasicAttribute.values()))
            .addAll(Arrays.asList(CombatAttribute.values()))
            .addAll(Arrays.asList(GeneralAttribute.values()))
            .addAll(Arrays.asList(SkillAttribute.values()))
            .addAll(Arrays.asList(SpecialAttribute.values()))
            .build();

    public Set<Attribute> getAllAttributes() {
        return ALL_ATTRIBUTES_SET;
    }
}
