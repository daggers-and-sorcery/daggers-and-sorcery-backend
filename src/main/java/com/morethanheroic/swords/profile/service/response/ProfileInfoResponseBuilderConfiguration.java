package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import javax.servlet.http.HttpSession;

@Builder
@Getter
public class ProfileInfoResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private UserEntity userEntity;
    //TODO: Remove this. We don't want to leak the session out this way to the business layer!
    private HttpSession httpSession;
}
