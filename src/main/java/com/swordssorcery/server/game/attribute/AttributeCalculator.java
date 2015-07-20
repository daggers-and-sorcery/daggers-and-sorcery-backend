package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.model.db.User;

public interface AttributeCalculator {

    AttributeData calculateAttributeValue(User user, Attribute attribute);
}
