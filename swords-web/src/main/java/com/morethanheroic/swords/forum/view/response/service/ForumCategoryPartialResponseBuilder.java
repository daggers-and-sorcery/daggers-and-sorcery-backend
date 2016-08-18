package com.morethanheroic.swords.forum.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponse;
import com.morethanheroic.swords.skill.view.response.domain.configuration.SkillLevelPartialResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 2016. 08. 01..
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ForumPartialResponseBuilder implements PartialResponseBuilder<ForumPartialResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;

    @Override
    public PartialResponse build(ForumPartialResponseBuilderConfiguration forumPartialResponseBuilderConfiguration) {

    }
}