package com.morethanheroic.swords.profile.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.domain.type.GeneralAttributeType;
import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeDefinitionPartialResponse extends PartialResponse {

    private final Attribute id;
    private final String name;
    private final int initialValue;
    private final AttributeType attributeType;
    private final GeneralAttributeType generalAttributeType;
    private final boolean hasPage;
}
