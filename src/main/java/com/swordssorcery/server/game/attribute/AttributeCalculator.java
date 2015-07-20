package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;

public interface AttributeCalculator {

    AttributeData calculateAttributeValue(UserDatabaseEntity user, Attribute attribute);
}
