package com.morethanheroic.swords.skill.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SkillListEntryPartialResponse extends PartialResponse {

    private String id;
    private String name;
}
