package com.morethanheroic.swords.quest.view.request.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Setter
@Getter
public class AcceptQuestRequest {

    @Min(1)
    private int questId;
}
