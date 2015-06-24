package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.type.BasicAttribute;
import com.swordssorcery.server.game.attribute.type.CombatAttribute;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class AttributeUtil {

    private Set<Attribute> attributeSet = new HashSet<>();

    public AttributeUtil() {
        attributeSet.addAll(Arrays.asList(BasicAttribute.values()));
        attributeSet.addAll(Arrays.asList(CombatAttribute.values()));
        attributeSet.addAll(Arrays.asList(GeneralAttribute.values()));
    }

    public Set<Attribute> getAllAttributes() {
        return attributeSet;
    }
}
