package com.morethanheroic.swords.spell.service.page;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@Getter
@RequiredArgsConstructor
public class PageEntryDataContainer {

    private final UserEntity userEntity;
    private final SessionEntity sessionEntity;
}
