package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.type.BasicAttribute;
import com.swordssorcery.server.game.attribute.type.CombatAttribute;
import com.swordssorcery.server.game.attribute.type.GeneralAttribute;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AttributeUtil {

    public Set<Attribute> getAllAttributes() {
        Set<Attribute> attributeList = new HashSet<>();

        attributeList.addAll(Arrays.asList(BasicAttribute.values()));
        attributeList.addAll(Arrays.asList(CombatAttribute.values()));
        attributeList.addAll(Arrays.asList(GeneralAttribute.values()));

        return attributeList;
    }
}
