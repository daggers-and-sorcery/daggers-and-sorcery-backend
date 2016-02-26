package com.morethanheroic.swords.user.service.event;

import com.morethanheroic.login.domain.event.LoginEventConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handle a successful login event.
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginEventHandler extends com.morethanheroic.login.service.event.LoginEventHandler {

    @NonNull
    private final UserFacade userFacade;

    @Override
    public void onEvent(LoginEventConfiguration loginEventConfiguration) {
        userFacade.updateLastLoginTime((UserEntity) loginEventConfiguration.getUserEntity());
    }
}
