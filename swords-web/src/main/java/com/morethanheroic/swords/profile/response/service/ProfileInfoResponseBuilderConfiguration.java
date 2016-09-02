package com.morethanheroic.swords.profile.response.service;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import javax.servlet.http.HttpSession;

@Builder
@Getter
public class ProfileInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    private SessionEntity sessionEntity;
}
