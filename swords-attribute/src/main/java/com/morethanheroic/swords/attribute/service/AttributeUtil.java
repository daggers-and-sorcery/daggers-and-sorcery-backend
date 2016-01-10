package com.morethanheroic.swords.attribute.service;

import com.google.common.collect.ImmutableSet;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class AttributeUtil {

    private final Set<Attribute> attributeSet;

    public AttributeUtil() {
        final Set<Attribute> temporarySet = new LinkedHashSet<>();

        temporarySet.addAll(Arrays.asList(BasicAttribute.values()));
        temporarySet.addAll(Arrays.asList(CombatAttribute.values()));
        temporarySet.addAll(Arrays.asList(GeneralAttribute.values()));
        temporarySet.addAll(Arrays.asList(SkillAttribute.values()));

        attributeSet = ImmutableSet.copyOf(temporarySet);
    }

    public Set<Attribute> getAllAttributes() {
        return attributeSet;
    }
}
