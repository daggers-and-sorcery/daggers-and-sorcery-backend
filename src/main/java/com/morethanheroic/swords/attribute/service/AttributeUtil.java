package com.morethanheroic.swords.attribute.service;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.domain.Attribute;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class AttributeUtil {

    private Set<Attribute> attributeSet = new HashSet<>();

    public AttributeUtil() {
        attributeSet.addAll(Arrays.asList(BasicAttribute.values()));
        attributeSet.addAll(Arrays.asList(CombatAttribute.values()));
        attributeSet.addAll(Arrays.asList(GeneralAttribute.values()));
        attributeSet.addAll(Arrays.asList(SkillAttribute.values()));
    }

    public Set<Attribute> getAllAttributes() {
        return attributeSet;
    }
}
