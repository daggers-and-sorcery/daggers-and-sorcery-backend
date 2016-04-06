package com.morethanheroic.swords.spell.service.page;

import com.morethanheroic.swords.user.domain.UserEntity;

import javax.servlet.http.HttpSession;

public class PageEntryDataContainer {

    private final UserEntity userEntity;
    private final HttpSession httpSession;

    public PageEntryDataContainer(UserEntity userEntity, HttpSession httpSession) {
        this.userEntity = userEntity;
        this.httpSession = httpSession;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }
}
